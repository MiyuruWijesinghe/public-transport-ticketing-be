package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.Inspector;

@Repository
public interface InspectorRepository extends MongoRepository<Inspector, Integer> {

}
