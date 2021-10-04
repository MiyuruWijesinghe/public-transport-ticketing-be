package com.csse.publictransport.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csse.publictransport.exception.NoRecordFoundException;
import com.csse.publictransport.exception.ValidateRecordException;
import com.csse.publictransport.model.BusRouteMap;
import com.csse.publictransport.model.BusTour;
import com.csse.publictransport.repository.BusRouteMapRepository;
import com.csse.publictransport.repository.BusTourRepository;
import com.csse.publictransport.resource.BusTourResource;
import com.csse.publictransport.service.BusTourService;
import com.csse.publictransport.service.CommonService;

@Component
@Transactional(rollbackFor=Exception.class)
public class BusTourServiceImpl implements BusTourService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BusRouteMapRepository busRouteMapRepository;
	
	@Autowired
	private BusTourRepository busTourRepository;
	
	@Override
	public List<BusTour> findAll() {
		return busTourRepository.findAll();
	}

	@Override
	public Optional<BusTour> findById(String id) {
		Optional<BusTour> busTour = busTourRepository.findById(id);
		if (busTour.isPresent()) {
			return Optional.ofNullable(busTour.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<BusTour> findByBusId(String busId) {
		return busTourRepository.findByBusRouteMapBusId(busId);
	}

	@Override
	public String saveBusTour(BusTourResource busTourResource) {
		BusTour busTour = new BusTour();
        
        Optional<BusRouteMap> busRouteMap = busRouteMapRepository.findByBusIdAndBusRouteId(busTourResource.getBusId(), busTourResource.getBusRouteId());
		if (!busRouteMap.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("busRouteMap.invalid-value"), "message");
		} else {
			busTour.setBusRouteMap(busRouteMap.get());
		}
		
		busTour.setDate(busTourResource.getDate());
		busTour.setStartTime(busTourResource.getStartTime());
		busTour.setEndTime(busTourResource.getEndTime());
		busTour.setDirection(busTourResource.getDirection());
		busTour.setTourRevenue(new BigDecimal(busTourResource.getTourRevenue()));
		busTour.setTotalPassengers(Long.parseLong(busTourResource.getTotalPassengers()));
        //bus.setCreatedUser(authTokenFilter.getUsername());
		busTour.setCreatedUser("MKW");
		busTour.setCreatedDate(commonService.formatDate(new Date()));
		busTourRepository.save(busTour);
		return busTour.getId();
	}

	@Override
	public BusTour updateBusTour(String id, BusTourResource busTourResource) {
		Optional<BusTour> isPresentBusTour = busTourRepository.findById(id);
		if (!isPresentBusTour.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		BusTour busTour = isPresentBusTour.get();
		
		Optional<BusRouteMap> busRouteMap = busRouteMapRepository.findByBusIdAndBusRouteId(busTourResource.getBusId(), busTourResource.getBusRouteId());
		if (!busRouteMap.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("busRouteMap.invalid-value"), "message");
		} else {
			busTour.setBusRouteMap(busRouteMap.get());
		}
		
		busTour.setDate(busTourResource.getDate());
		busTour.setStartTime(busTourResource.getStartTime());
		busTour.setEndTime(busTourResource.getEndTime());
		busTour.setDirection(busTourResource.getDirection());
		busTour.setTourRevenue(new BigDecimal(busTourResource.getTourRevenue()));
		busTour.setTotalPassengers(Long.parseLong(busTourResource.getTotalPassengers()));
		busTour.setModifiedUser("MKW");
        busTour.setModifiedDate(commonService.formatDate(new Date()));
		busTourRepository.save(busTour);
		return busTour;
	}

	@Override
	public String deleteBusTour(String id) {
		Optional<BusTour> isPresentBusTour = busTourRepository.findById(id);
		if (!isPresentBusTour.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		busTourRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

}
