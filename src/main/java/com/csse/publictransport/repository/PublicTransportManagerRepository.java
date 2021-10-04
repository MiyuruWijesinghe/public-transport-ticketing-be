package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.PublicTransportManager;

@Repository
public interface PublicTransportManagerRepository extends MongoRepository<PublicTransportManager, String> {

}
