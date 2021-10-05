package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.csse.publictransport.model.BusToken;
import com.csse.publictransport.resource.BusTokenRequestResource;

/**
 * Bus Token Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MenukaJ   IT19004914     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface BusTokenService {


	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<BusToken> findAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<BusToken> findById(String id);

	/**
	 * Find by status.
	 *
	 * @param status the status
	 * @return the list
	 */
	public List<BusToken> findByStatus(String status);

	/**
	 * Save bus token.
	 *
	 * @param busTokenRequestResource the bus token request resource
	 * @return the string
	 */
	public String saveBusToken(BusTokenRequestResource busTokenRequestResource);

	/**
	 * Update bus token.
	 *
	 * @param id the id
	 * @param busTokenRequestResource the bus token request resource
	 * @return the bus token
	 */
	public BusToken updateBusToken(String id, BusTokenRequestResource busTokenRequestResource);

	/**
	 * Delete bus token.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String deleteBusToken(String id);
	
	/**
	 * Validate bus token.
	 *
	 * @param qrCode the qr code
	 * @return the boolean
	 */
	public Boolean validateBusToken(String qrCode);

}
