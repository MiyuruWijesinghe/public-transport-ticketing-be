package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.csse.publictransport.model.BusRouteMap;
import com.csse.publictransport.resource.BusRouteMapResource;

/**
 * Bus Route Map Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface BusRouteMapService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<BusRouteMap> findAll();

	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<BusRouteMap> findById(String id);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<BusRouteMap> findByStatus(String status);
	
	
	/**
	 * Find by bus id.
	 *
	 * @param busId - the bus id
	 * @return the list
	 */
	public List<BusRouteMap> findByBusId(String busId);
	
	
	/**
	 * Find by route id.
	 *
	 * @param routeId - the route id
	 * @return the list
	 */
	public List<BusRouteMap> findByRouteId(String routeId);
	
	
	/**
	 * Find by bus id and bus route id.
	 *
	 * @param busId - the bus id
	 * @param busRouteId - the bus route id
	 * @return the optional
	 */
	public Optional<BusRouteMap> findByBusIdAndBusRouteId(String busId, String busRouteId);
	
	
	/**
	 * Save bus route map.
	 *
	 * @param busRouteMapResource - the bus route map resource
	 * @return the string
	 */
	public String saveBusRouteMap(BusRouteMapResource busRouteMapResource);
	
	
	/**
	 * Update bus route map.
	 *
	 * @param id - the id
	 * @param busRouteMapResource - the bus route map resource
	 * @return the bus route map
	 */
	public BusRouteMap updateBusRouteMap(String id, BusRouteMapResource busRouteMapResource);

	
	/**
	 * Delete bus route map.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteBusRouteMap(String id);
	
}
