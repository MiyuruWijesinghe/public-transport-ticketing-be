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
import com.csse.publictransport.model.BusRoute;
import com.csse.publictransport.repository.BusRouteRepository;
import com.csse.publictransport.resource.BusRouteResource;
import com.csse.publictransport.security.jwt.AuthTokenFilter;
import com.csse.publictransport.service.BusRouteService;
import com.csse.publictransport.service.CommonService;

/**
 * Bus Route Service Implementation
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class BusRouteServiceImpl implements BusRouteService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BusRouteRepository busRouteRepository;
	
	@Override
	public List<BusRoute> findAll() {
		return busRouteRepository.findAll();
	}

	@Override
	public Optional<BusRoute> findById(String id) {
		Optional<BusRoute> busRoute = busRouteRepository.findById(id);
		if (busRoute.isPresent()) {
			return Optional.ofNullable(busRoute.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<BusRoute> findByRouteNo(String routeNo) {
		Optional<BusRoute> busRoute = busRouteRepository.findByRouteNo(routeNo);
		if (busRoute.isPresent()) {
			return Optional.ofNullable(busRoute.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<BusRoute> findByStatus(String status) {
		return busRouteRepository.findByStatus(status);
	}

	@Override
	public String saveBusRoute(BusRouteResource busRouteResource) {
		BusRoute busRoute = new BusRoute();
		
        if (busRouteRepository.existsByRouteNo(busRouteResource.getRouteNo())) {
        	throw new ValidateRecordException(environment.getProperty("busRoute.duplicate"), "message");
		}
		
        busRoute.setRouteNo(busRouteResource.getRouteNo());
        busRoute.setStartingPoint(busRouteResource.getStartingPoint());
        busRoute.setDestination(busRouteResource.getDestination());
        busRoute.setTotalDistance(new BigDecimal(busRouteResource.getTotalDistance()));
        busRoute.setStatus(busRouteResource.getStatus());
        busRoute.setCreatedUser(authTokenFilter.getUsername());
        busRoute.setCreatedDate(commonService.formatDate(new Date()));
        busRouteRepository.save(busRoute);
		return busRoute.getId();
	}

	@Override
	public BusRoute updateBusRoute(String id, BusRouteResource busRouteResource) {
		Optional<BusRoute> isPresentBusRoute = busRouteRepository.findById(id);
		if (!isPresentBusRoute.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (busRouteRepository.existsByRouteNoAndIdNotIn(busRouteResource.getRouteNo(), id)) {
        	throw new ValidateRecordException(environment.getProperty("busRoute.duplicate"), "message");
		}
		
		BusRoute busRoute = isPresentBusRoute.get();
		busRoute.setRouteNo(busRouteResource.getRouteNo());
		busRoute.setStartingPoint(busRouteResource.getStartingPoint());
        busRoute.setDestination(busRouteResource.getDestination());
        busRoute.setTotalDistance(new BigDecimal(busRouteResource.getTotalDistance()));
		busRoute.setStatus(busRouteResource.getStatus());
		busRoute.setModifiedUser(authTokenFilter.getUsername());
        busRoute.setModifiedDate(commonService.formatDate(new Date()));
		busRouteRepository.save(busRoute);
		return busRoute;
	}

	@Override
	public String deleteBusRoute(String id) {
		Optional<BusRoute> isPresentBusRoute = busRouteRepository.findById(id);
		if (!isPresentBusRoute.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		busRouteRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}
		
}
