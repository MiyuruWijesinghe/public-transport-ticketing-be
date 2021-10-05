package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.csse.publictransport.model.PublicTransportAccount;
import com.csse.publictransport.resource.PublicTransportAccountAddResource;
import com.csse.publictransport.resource.PublicTransportAccountResource;
import com.csse.publictransport.resource.PublicTransportAccountUpdateResource;

/**
 * Public Transport Account Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface PublicTransportAccountService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<PublicTransportAccount> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<PublicTransportAccount> findById(String id);
	
	
	/**
	 * Find by account no.
	 *
	 * @param accountNo - the account no
	 * @return the optional
	 */
	public Optional<PublicTransportAccount> findByAccountNo(String accountNo);
	
	
	/**
	 * Find by user id.
	 *
	 * @param userId - the user id
	 * @return the optional
	 */
	public Optional<PublicTransportAccount> findByUserId(String userId);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<PublicTransportAccount> findByStatus(String status);
	
	
	/**
	 * Save public transport account.
	 *
	 * @param publicTransportAccountAddResource - the public transport account add resource
	 * @return the string
	 */
	public String savePublicTransportAccount(PublicTransportAccountAddResource publicTransportAccountAddResource);
	
	
	/**
	 * Update public transport account.
	 *
	 * @param id - the id
	 * @param publicTransportAccountUpdateResource - the public transport account update resource
	 * @return the public transport account
	 */
	public PublicTransportAccount updatePublicTransportAccount(String id, PublicTransportAccountUpdateResource publicTransportAccountUpdateResource);
	
	
	/**
	 * Recharge or withdraw public transport account.
	 *
	 * @param publicTransportAccountResource - the public transport account resource
	 * @return the public transport account
	 */
	public PublicTransportAccount rechargeOrWithdrawPublicTransportAccount(PublicTransportAccountResource publicTransportAccountResource);

	
	/**
	 * Delete public transport account.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deletePublicTransportAccount(String id);
}
