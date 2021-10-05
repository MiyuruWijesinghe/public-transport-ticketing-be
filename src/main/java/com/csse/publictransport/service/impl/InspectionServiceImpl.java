package com.csse.publictransport.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.csse.publictransport.enums.CommonStatus;
import com.csse.publictransport.exception.NoRecordFoundException;
import com.csse.publictransport.exception.ValidateRecordException;
import com.csse.publictransport.model.BusRoute;
import com.csse.publictransport.model.Inspection;
import com.csse.publictransport.model.Users;
import com.csse.publictransport.repository.BusRouteRepository;
import com.csse.publictransport.repository.InspectionRepository;
import com.csse.publictransport.repository.UserRepository;
import com.csse.publictransport.resource.InspectionResource;
import com.csse.publictransport.service.InspectionService;

/**
 * Inspection Service Implementation
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
public class InspectionServiceImpl implements InspectionService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BusRouteRepository busRouteRepository;
	
	@Autowired
	private InspectionRepository inspectionRepository;
	
	@Override
	public List<Inspection> findAll() {
		return inspectionRepository.findAll();
	}

	@Override
	public Optional<Inspection> findById(String id) {
		Optional<Inspection> inspection = inspectionRepository.findById(id);
		if (inspection.isPresent()) {
			return Optional.ofNullable(inspection.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Inspection> findByUserId(String userId) {
		return inspectionRepository.findByUsersId(userId);
	}

	@Override
	public List<Inspection> findByBusRouteId(String routeId) {
		return inspectionRepository.findByBusRouteId(routeId);
	}

	@Override
	public Optional<Inspection> findByUserIdAndBusRouteId(String userId, String busRouteId) {
		Optional<Inspection> inspection = inspectionRepository.findByUsersIdAndBusRouteId(userId, busRouteId);
		if (inspection.isPresent()) {
			return Optional.ofNullable(inspection.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public String saveInspection(InspectionResource inspectionResource) {
		Inspection inspection = new Inspection();
		
        if (inspectionRepository.existsByUsersIdAndBusRouteId(inspectionResource.getInspectorId(), inspectionResource.getBusRouteId())) {
        	throw new ValidateRecordException(environment.getProperty("inspection.duplicate"), "message");
		}
        
        Optional<Users> users = userRepository.findByIdAndStatus(inspectionResource.getInspectorId(), CommonStatus.ACTIVE.toString());
		if (!users.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("user.invalid-value"), "message");
		} else {
			inspection.setUsers(users.get());
		}
        
		Optional<BusRoute> busRoute = busRouteRepository.findByIdAndStatus(inspectionResource.getBusRouteId(), CommonStatus.ACTIVE.toString());
		if (!busRoute.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("busRoute.invalid-value"), "message");
		} else {
			inspection.setBusRoute(busRoute.get());
		}
		
		inspection.setDate(inspectionResource.getDate());
		inspection.setStartTime(inspectionResource.getStartTime());
		inspection.setEndTime(inspectionResource.getEndTime());
		inspection.setNumOfInspections(Long.parseLong(inspectionResource.getNumOfInspections()));
		inspectionRepository.save(inspection);
		return inspection.getId();
	}

	@Override
	public Inspection updateInspection(String id, InspectionResource inspectionResource) {
		Optional<Inspection> isPresentInspection = inspectionRepository.findById(id);
		if (!isPresentInspection.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (inspectionRepository.existsByUsersIdAndBusRouteIdAndIdNotIn(inspectionResource.getInspectorId(), inspectionResource.getBusRouteId(), id)) {
        	throw new ValidateRecordException(environment.getProperty("busRouteMap.duplicate"), "message");
		}
		
		Inspection inspection = isPresentInspection.get();
		
		Optional<Users> users = userRepository.findByIdAndStatus(inspectionResource.getInspectorId(), CommonStatus.ACTIVE.toString());
		if (!users.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("user.invalid-value"), "message");
		} else {
			inspection.setUsers(users.get());
		}
        
		Optional<BusRoute> busRoute = busRouteRepository.findByIdAndStatus(inspectionResource.getBusRouteId(), CommonStatus.ACTIVE.toString());
		if (!busRoute.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("busRoute.invalid-value"), "message");
		} else {
			inspection.setBusRoute(busRoute.get());
		}
		
		inspection.setDate(inspectionResource.getDate());
		inspection.setStartTime(inspectionResource.getStartTime());
		inspection.setEndTime(inspectionResource.getEndTime());
		inspection.setNumOfInspections(Long.parseLong(inspectionResource.getNumOfInspections()));
		inspectionRepository.save(inspection);
		return inspection;
	}

	@Override
	public String deleteInspection(String id) {
		Optional<Inspection> isPresentInspection = inspectionRepository.findById(id);
		if (!isPresentInspection.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		inspectionRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

}
