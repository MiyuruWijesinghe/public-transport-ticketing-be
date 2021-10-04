package com.csse.publictransport.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.Roles;


/**
 * Roles Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface RolesRepository extends MongoRepository<Roles, String> {

	public Optional<Roles> findByIdAndStatus(String id, String status);

	public Optional<Roles> findByName(String name);

}
