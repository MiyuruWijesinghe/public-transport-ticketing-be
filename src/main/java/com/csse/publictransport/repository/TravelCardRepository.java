package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.csse.publictransport.model.TravelCard;

@Repository
public interface TravelCardRepository extends MongoRepository<TravelCard, Integer> {

}
