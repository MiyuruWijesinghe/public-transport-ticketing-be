package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.Bus;

@Repository
public interface BusRepository extends MongoRepository<Bus, Integer> {

}
