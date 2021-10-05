package com.csse.publictransport.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.BusTour;

/**
 * Bus Tour Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface BusTourRepository extends MongoRepository<BusTour, String> {

	public List<BusTour> findByBusRouteMapBusId(String busId);
	
	

}
