package com.csse.publictransport.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.csse.publictransport.enums.CommonStatus;
import com.csse.publictransport.exception.NoRecordFoundException;
import com.csse.publictransport.exception.ValidateRecordException;
import com.csse.publictransport.model.Bus;
import com.csse.publictransport.model.BusRoute;
import com.csse.publictransport.model.BusRouteMap;
import com.csse.publictransport.repository.BusRepository;
import com.csse.publictransport.repository.BusRouteMapRepository;
import com.csse.publictransport.repository.BusRouteRepository;
import com.csse.publictransport.resource.BusRouteMapResource;
import com.csse.publictransport.security.jwt.AuthTokenFilter;
import com.csse.publictransport.service.BusRouteMapService;
import com.csse.publictransport.service.CommonService;

@Component
@Transactional(rollbackFor=Exception.class)
public class BusRouteMapServiceImpl implements BusRouteMapService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private BusRouteRepository busRouteRepository;
	
	@Autowired
	private BusRouteMapRepository busRouteMapRepository;
	
	@Override
	public List<BusRouteMap> findAll() {
		return busRouteMapRepository.findAll();
	}

	@Override
	public Optional<BusRouteMap> findById(String id) {
		Optional<BusRouteMap> busRouteMap = busRouteMapRepository.findById(id);
		if (busRouteMap.isPresent()) {
			return Optional.ofNullable(busRouteMap.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<BusRouteMap> findByStatus(String status) {
		return busRouteMapRepository.findByStatus(status);
	}

	@Override
	public List<BusRouteMap> findByBusId(String busId) {
		return busRouteMapRepository.findByBusId(busId);
	}

	@Override
	public List<BusRouteMap> findByRouteId(String routeId) {
		return busRouteMapRepository.findByBusRouteId(routeId);
	}

	@Override
	public Optional<BusRouteMap> findByBusIdAndBusRouteId(String busId, String busRouteId) {
		Optional<BusRouteMap> busRouteMap = busRouteMapRepository.findByBusIdAndBusRouteId(busId, busRouteId);
		if (busRouteMap.isPresent()) {
			return Optional.ofNullable(busRouteMap.get());
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public String saveBusRouteMap(BusRouteMapResource busRouteMapResource) {
		BusRouteMap busRouteMap = new BusRouteMap();
		
        if (busRouteMapRepository.existsByBusIdAndBusRouteId(busRouteMapResource.getBusId(), busRouteMapResource.getBusRouteId())) {
        	throw new ValidateRecordException(environment.getProperty("busRouteMap.duplicate"), "message");
		}
        
        Optional<Bus> bus = busRepository.findByIdAndStatus(busRouteMapResource.getBusId(), CommonStatus.ACTIVE.toString());
		if (!bus.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("bus.invalid-value"), "message");
		} else {
			busRouteMap.setBus(bus.get());
		}
        
		Optional<BusRoute> busRoute = busRouteRepository.findByIdAndStatus(busRouteMapResource.getBusRouteId(), CommonStatus.ACTIVE.toString());
		if (!busRoute.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("busRoute.invalid-value"), "message");
		} else {
			busRouteMap.setBusRoute(busRoute.get());
		}
		
		busRouteMap.setStatus(busRouteMapResource.getStatus());
		busRouteMap.setCreatedUser(authTokenFilter.getUsername());
        busRouteMap.setCreatedDate(commonService.formatDate(new Date()));
        busRouteMapRepository.save(busRouteMap);
		return busRouteMap.getId();
	}

	@Override
	public BusRouteMap updateBusRouteMap(String id, BusRouteMapResource busRouteMapResource) {
		Optional<BusRouteMap> isPresentBusRouteMap = busRouteMapRepository.findById(id);
		if (!isPresentBusRouteMap.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (busRouteMapRepository.existsByBusIdAndBusRouteIdAndIdNotIn(busRouteMapResource.getBusId(), busRouteMapResource.getBusRouteId(), id)) {
        	throw new ValidateRecordException(environment.getProperty("busRouteMap.duplicate"), "message");
		}
		
		BusRouteMap busRouteMap = isPresentBusRouteMap.get();
		
		Optional<Bus> bus = busRepository.findByIdAndStatus(busRouteMapResource.getBusId(), CommonStatus.ACTIVE.toString());
		if (!bus.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("bus.invalid-value"), "message");
		} else {
			busRouteMap.setBus(bus.get());
		}
        
		Optional<BusRoute> busRoute = busRouteRepository.findByIdAndStatus(busRouteMapResource.getBusRouteId(), CommonStatus.ACTIVE.toString());
		if (!busRoute.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("busRoute.invalid-value"), "message");
		} else {
			busRouteMap.setBusRoute(busRoute.get());
		}
		
		busRouteMap.setStatus(busRouteMapResource.getStatus());
		busRouteMap.setModifiedUser(authTokenFilter.getUsername());
		busRouteMap.setModifiedDate(commonService.formatDate(new Date()));
		busRouteMapRepository.save(busRouteMap);
		return busRouteMap;
	}

	@Override
	public String deleteBusRouteMap(String id) {
		Optional<BusRouteMap> isPresentBusRouteMap = busRouteMapRepository.findById(id);
		if (!isPresentBusRouteMap.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		busRouteMapRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

}
