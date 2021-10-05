package com.csse.publictransport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.BusToken;

/**
 * Bus Token Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface BusTokenRepository extends MongoRepository<BusToken, String> {

	List<BusToken> findByStatus(String status);

	Optional<BusToken> findByqrcode(String qrCode);

	Optional<BusToken> findByqrcodeAndStatus(String qrCode, String string);

}
