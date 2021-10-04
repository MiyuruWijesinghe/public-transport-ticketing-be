package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.Journey;

@Repository
public interface JourneyRepository extends MongoRepository<Journey, Integer> {

}
