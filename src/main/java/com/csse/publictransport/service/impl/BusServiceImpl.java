package com.csse.publictransport.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.csse.publictransport.exception.NoRecordFoundException;
import com.csse.publictransport.exception.ValidateRecordException;
import com.csse.publictransport.model.Bus;
import com.csse.publictransport.repository.BusRepository;
import com.csse.publictransport.resource.BusResource;
import com.csse.publictransport.service.BusService;
import com.csse.publictransport.service.CommonService;
import com.csse.publictransport.security.jwt.AuthTokenFilter;

/**
 * Bus Service Implementation
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
public class BusServiceImpl implements BusService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BusRepository busRepository;
	
	@Override
	public List<Bus> findAll() {
		return busRepository.findAll();
	}

	@Override
	public Optional<Bus> findById(String id) {
		Optional<Bus> bus = busRepository.findById(id);
		if (bus.isPresent()) {
			return Optional.ofNullable(bus.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Bus> findByVehicleNo(String vehicleNo) {
		Optional<Bus> bus = busRepository.findByVehicleNo(vehicleNo);
		if (bus.isPresent()) {
			return Optional.ofNullable(bus.get());
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public List<Bus> findByStatus(String status) {
		return busRepository.findByStatus(status);
	}

	@Override
	public String saveBus(BusResource busResource) {
		Bus bus = new Bus();
		
        if (busRepository.existsByVehicleNo(busResource.getVehicleNo())) {
        	throw new ValidateRecordException(environment.getProperty("bus.duplicate"), "message");
		}
		
        bus.setVehicleNo(busResource.getVehicleNo());
        bus.setCapacity(Long.parseLong(busResource.getCapacity()));
        bus.setStatus(busResource.getStatus());
        bus.setCreatedUser(authTokenFilter.getUsername());
        bus.setCreatedDate(commonService.formatDate(new Date()));
        busRepository.save(bus);
		return bus.getId();
	}

	@Override
	public Bus updateBus(String id, BusResource busResource) {
		
		Optional<Bus> isPresentBus = busRepository.findById(id);
		if (!isPresentBus.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (busRepository.existsByVehicleNoAndIdNotIn(busResource.getVehicleNo(), id)) {
        	throw new ValidateRecordException(environment.getProperty("bus.duplicate"), "message");
		}
		
		Bus bus = isPresentBus.get();
		bus.setVehicleNo(busResource.getVehicleNo());
        bus.setCapacity(Long.parseLong(busResource.getCapacity()));
		bus.setStatus(busResource.getStatus());
		bus.setModifiedUser(authTokenFilter.getUsername());
        bus.setModifiedDate(commonService.formatDate(new Date()));
		busRepository.save(bus);
		return bus;
	}

	@Override
	public String deleteBus(String id) {
		Optional<Bus> isPresentBus = busRepository.findById(id);
		if (!isPresentBus.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		busRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}
	
}
