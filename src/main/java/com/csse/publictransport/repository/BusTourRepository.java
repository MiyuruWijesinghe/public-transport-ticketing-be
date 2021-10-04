package com.csse.publictransport.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.BusTour;

@Repository
public interface BusTourRepository extends MongoRepository<BusTour, String> {

	public List<BusTour> findByBusRouteMapBusId(String busId);
	
	

}
