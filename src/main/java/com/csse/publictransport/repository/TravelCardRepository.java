package com.csse.publictransport.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.csse.publictransport.model.TravelCard;

/**
 * Travel Card Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface TravelCardRepository extends MongoRepository<TravelCard, String> {

	List<TravelCard> findByStatus(String status);

}
