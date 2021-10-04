package com.csse.publictransport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.BusRoute;

@Repository
public interface BusRouteRepository extends MongoRepository<BusRoute, String> {

	public Optional<BusRoute> findByRouteNo(String routeNo);
	
	public List<BusRoute> findByStatus(String status);

	public Boolean existsByRouteNo(String routeNo);
	
	public Boolean existsByRouteNoAndIdNotIn(String routeNo, String id);

	public Optional<BusRoute> findByIdAndStatus(String id, String status);
}
