package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.csse.publictransport.model.BusRouteMap;
import com.csse.publictransport.resource.BusRouteMapResource;

@Service
public interface BusRouteMapService {

	public List<BusRouteMap> findAll();

	public Optional<BusRouteMap> findById(String id);
	
	public List<BusRouteMap> findByStatus(String status);
	
	public List<BusRouteMap> findByBusId(String busId);
	
	public List<BusRouteMap> findByRouteId(String routeId);
	
	public String saveBusRouteMap(BusRouteMapResource busRouteMapResource);
	
	public BusRouteMap updateBusRouteMap(String id, BusRouteMapResource busRouteMapResource);

	public String deleteBusRouteMap(String id);
}
