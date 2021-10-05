package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.csse.publictransport.model.TravelCard;
import com.csse.publictransport.resource.TravelCardRequestResource;

/**
 * Travel Card Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MenukaJ   IT19004914     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface TravelCardService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<TravelCard> findAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<TravelCard> findById(String id);

	/**
	 * Find by status.
	 *
	 * @param status the status
	 * @return the list
	 */
	public List<TravelCard> findByStatus(String status);

	/**
	 * Save travel card.
	 *
	 * @param travelCardRequestResource the travel card request resource
	 * @return the string
	 */
	public String saveTravelCard(TravelCardRequestResource travelCardRequestResource);

	/**
	 * Update travel card.
	 *
	 * @param id the id
	 * @param travelCardRequestResource the travel card request resource
	 * @return the travel card
	 */
	public TravelCard updateTravelCard(String id, TravelCardRequestResource travelCardRequestResource);

	/**
	 * Delete travel card.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String deleteTravelCard(String id);

}
