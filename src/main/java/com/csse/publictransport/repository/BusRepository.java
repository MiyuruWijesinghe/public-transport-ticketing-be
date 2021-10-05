package com.csse.publictransport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.Bus;

/**
 * Bus Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface BusRepository extends MongoRepository<Bus, String> {

	public Optional<Bus> findByVehicleNo(String vehicleNo);
	
	public List<Bus> findByStatus(String status);

	public Boolean existsByVehicleNo(String vehicleNo);
	
	public Boolean existsByVehicleNoAndIdNotIn(String vehicleNo, String id);

	public Optional<Bus> findByIdAndStatus(String id, String status);

}
