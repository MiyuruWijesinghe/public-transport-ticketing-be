package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.csse.publictransport.model.BusTour;
import com.csse.publictransport.resource.BusTourResource;

/**
 * Bus Tour Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface BusTourService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<BusTour> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<BusTour> findById(String id);
	
	
	/**
	 * Find by bus id.
	 *
	 * @param busId - the bus id
	 * @return the list
	 */
	public List<BusTour> findByBusId(String busId);
	
	
	/**
	 * Save bus tour.
	 *
	 * @param busTourResource - the bus tour resource
	 * @return the string
	 */
	public String saveBusTour(BusTourResource busTourResource);
	
	
	/**
	 * Update bus tour.
	 *
	 * @param id - the id
	 * @param busTourResource - the bus tour resource
	 * @return the bus tour
	 */
	public BusTour updateBusTour(String id, BusTourResource busTourResource);
	
	
	/**
	 * Delete bus tour.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteBusTour(String id);
	
}
