package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.csse.publictransport.model.Inspection;
import com.csse.publictransport.resource.InspectionResource;

/**
 * Inspection Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface InspectionService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Inspection> findAll();

	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Inspection> findById(String id);
	
	
	/**
	 * Find by user id.
	 *
	 * @param userId - the user id
	 * @return the list
	 */
	public List<Inspection> findByUserId(String userId);

	
	/**
	 * Find by bus route id.
	 *
	 * @param routeId - the route id
	 * @return the list
	 */
	public List<Inspection> findByBusRouteId(String routeId);
	
	
	/**
	 * Find by user id and bus route id.
	 *
	 * @param userId - the user id
	 * @param busRouteId - the bus route id
	 * @return the optional
	 */
	public Optional<Inspection> findByUserIdAndBusRouteId(String userId, String busRouteId);
	
	
	/**
	 * Save inspection.
	 *
	 * @param inspectionResource - the inspection resource
	 * @return the string
	 */
	public String saveInspection(InspectionResource inspectionResource);
	
	
	/**
	 * Update inspection.
	 *
	 * @param id - the id
	 * @param inspectionResource - the inspection resource
	 * @return the inspection
	 */
	public Inspection updateInspection(String id, InspectionResource inspectionResource);

	
	/**
	 * Delete inspection.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteInspection(String id);
	
}
