package com.csse.publictransport.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.csse.publictransport.model.Inspection;

/**
 * Inspection Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface InspectionRepository extends MongoRepository<Inspection, String> {

	public List<Inspection> findByUsersId(String userId);

	public List<Inspection> findByBusRouteId(String routeId);
	
	public Optional<Inspection> findByUsersIdAndBusRouteId(String userId, String busRouteId);

	public Boolean existsByUsersIdAndBusRouteId(String userId, String busRouteId);
	
	public Boolean existsByUsersIdAndBusRouteIdAndIdNotIn(String userId, String busRouteId, String id);
	
}
