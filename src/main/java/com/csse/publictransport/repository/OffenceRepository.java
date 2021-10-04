package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.Offence;

@Repository
public interface OffenceRepository extends MongoRepository<Offence, Integer> {

}
