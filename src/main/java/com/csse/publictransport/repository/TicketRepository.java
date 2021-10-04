package com.csse.publictransport.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, Integer> {

}
