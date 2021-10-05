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

	public List<Bus> findAll();
	
	
	public Optional<Bus> findById(String id);
	
	
	public Optional<Bus> findByVehicleNo(String vehicleNo);
	
	
	public List<Bus> findByStatus(String status);
	
	
	public String saveBus(BusResource busResource);
	
	
	public Bus updateBus(String id, BusResource busResource);
	
	
	public String deleteBus(String id);
	
}
