package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.PublicTransportAccount;

@Repository
public interface PublicTransportAccountRepository extends MongoRepository<PublicTransportAccount, Integer> {

}
