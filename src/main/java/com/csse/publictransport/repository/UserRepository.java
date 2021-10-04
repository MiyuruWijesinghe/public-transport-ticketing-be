package com.csse.publictransport.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.csse.publictransport.model.Users;



@Repository
public interface UserRepository extends MongoRepository<Users, Integer> {

	public Optional<Users> findByUsername(String username);
}
