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

	public List<BusRoute> findAll();
	
	
	public Optional<BusRoute> findById(String id);
	
	
	public Optional<BusRoute> findByRouteNo(String routeNo);
	
	
	public List<BusRoute> findByStatus(String status);
	
	
	public String saveBusRoute(BusRouteResource busRouteResource);
	
	
	public BusRoute updateBusRoute(String id, BusRouteResource busRouteResource);
	
	
	public String deleteBusRoute(String id);
}
