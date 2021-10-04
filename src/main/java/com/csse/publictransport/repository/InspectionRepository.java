package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.Inspection;

@Repository
public interface InspectionRepository extends MongoRepository<Inspection, Integer> {

}
