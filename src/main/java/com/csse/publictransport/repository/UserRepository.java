package com.csse.publictransport.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.csse.publictransport.model.Users;

/**
 * User Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface UserRepository extends MongoRepository<Users, Integer> {

	public Optional<Users> findByUsername(String username);

	public Optional<Users> findByIdAndStatus(String userId, String status);
}
