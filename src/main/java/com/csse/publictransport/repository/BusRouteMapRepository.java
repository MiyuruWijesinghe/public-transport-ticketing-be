package com.csse.publictransport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.BusRouteMap;

/**
 * Bus Route Map Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface BusRouteMapRepository extends MongoRepository<BusRouteMap, String> {

	public List<BusRouteMap> findByStatus(String status);

	public List<BusRouteMap> findByBusId(String busId);

	public List<BusRouteMap> findByBusRouteId(String routeId);
	
	public Optional<BusRouteMap> findByBusIdAndBusRouteId(String busId, String busRouteId);

	public Boolean existsByBusIdAndBusRouteId(String busId, String busRouteId);
	
	public Boolean existsByBusIdAndBusRouteIdAndIdNotIn(String busId, String busRouteId, String id);
	
}
