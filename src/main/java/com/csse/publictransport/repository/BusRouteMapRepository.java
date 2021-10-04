package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.BusRouteMap;

@Repository
public interface BusRouteMapRepository extends MongoRepository<BusRouteMap, String> {

}