package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.csse.publictransport.model.BusRoute;
import com.csse.publictransport.resource.BusRouteResource;

/**
 * Bus Route Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface BusRouteService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<BusRoute> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<BusRoute> findById(String id);
	
	
	/**
	 * Find by route no.
	 *
	 * @param routeNo - the route no
	 * @return the optional
	 */
	public Optional<BusRoute> findByRouteNo(String routeNo);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<BusRoute> findByStatus(String status);
	
	
	/**
	 * Save bus route.
	 *
	 * @param busRouteResource - the bus route resource
	 * @return the string
	 */
	public String saveBusRoute(BusRouteResource busRouteResource);
	
	
	/**
	 * Update bus route.
	 *
	 * @param id - the id
	 * @param busRouteResource - the bus route resource
	 * @return the bus route
	 */
	public BusRoute updateBusRoute(String id, BusRouteResource busRouteResource);
	
	
	/**
	 * Delete bus route.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteBusRoute(String id);
	
}
