package com.csse.publictransport.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.BusRouteMap;

@Repository
public interface BusRouteMapRepository extends MongoRepository<BusRouteMap, String> {

	public List<BusRouteMap> findByStatus(String status);

	public List<BusRouteMap> findByBusId(String busId);

	public List<BusRouteMap> findByBusRouteId(String routeId);

	public Boolean existsByBusIdAndBusRouteId(String busId, String busRouteId);
	
	public Boolean existsByBusIdAndBusRouteIdAndIdNotIn(String busId, String busRouteId, String id);
	
}
