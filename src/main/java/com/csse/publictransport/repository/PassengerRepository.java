package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.Passenger;

@Repository
public interface PassengerRepository extends MongoRepository<Passenger, Integer> {

}
