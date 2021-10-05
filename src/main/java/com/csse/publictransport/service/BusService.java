package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.csse.publictransport.model.Bus;
import com.csse.publictransport.resource.BusResource;

/**
 * Bus Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface BusService {

	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Bus> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Bus> findById(String id);
		
	
	/**
	 * Find by vehicle no.
	 *
	 * @param vehicleNo - the vehicle no
	 * @return the optional
	 */
	public Optional<Bus> findByVehicleNo(String vehicleNo);
		
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<Bus> findByStatus(String status);
	
	
	/**
	 * Save bus.
	 *
	 * @param busResource - the bus resource
	 * @return the string
	 */
	public String saveBus(BusResource busResource);
	
	
	/**
	 * Update bus.
	 *
	 * @param id - the id
	 * @param busResource - the bus resource
	 * @return the bus
	 */
	public Bus updateBus(String id, BusResource busResource);
	
	
	/**
	 * Delete bus.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteBus(String id);
	
}
