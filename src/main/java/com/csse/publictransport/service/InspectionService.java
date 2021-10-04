package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.csse.publictransport.model.Inspection;
import com.csse.publictransport.resource.InspectionResource;

@Service
public interface InspectionService {

	public List<Inspection> findAll();

	public Optional<Inspection> findById(String id);
	
	public List<Inspection> findByUserId(String userId);

	public List<Inspection> findByBusRouteId(String routeId);
	
	public Optional<Inspection> findByUserIdAndBusRouteId(String userId, String busRouteId);
	
	public String saveInspection(InspectionResource inspectionResource);
	
	public Inspection updateInspection(String id, InspectionResource inspectionResource);

	public String deleteInspection(String id);
	
}
