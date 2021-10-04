package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.BusRoute;

@Repository
public interface BusRouteRepository extends MongoRepository<BusRoute, Integer> {

}
