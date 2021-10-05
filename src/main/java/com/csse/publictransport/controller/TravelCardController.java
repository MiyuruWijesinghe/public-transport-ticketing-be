package com.csse.publictransport.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csse.publictransport.model.TravelCard;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.resource.TravelCardRequestResource;
import com.csse.publictransport.service.TravelCardService;


/**
 * Travel Card Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MenukaJ   IT19004914     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/travel-card")
@CrossOrigin(origins = "*")
public class TravelCardController {

	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The travel card service. */
	@Autowired
	private TravelCardService travelCardService;


	/**
	 * Gets the all travel cards.
	 *
	 * @return the all travel cards
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllTravelCards() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<TravelCard> travelCard = travelCardService.findAll();
		if (!travelCard.isEmpty()) {
			return new ResponseEntity<>((Collection<TravelCard>) travelCard, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the travel card by id.
	 *
	 * @param id the id
	 * @return the travel card by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getTravelCardById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<TravelCard> isPresentTravelCard = travelCardService.findById(id);
		if (isPresentTravelCard.isPresent()) {
			return new ResponseEntity<>(isPresentTravelCard, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	

	/**
	 * Gets the travel card by status.
	 *
	 * @param status the status
	 * @return the travel card by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getTravelCardByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<TravelCard> travelCard = travelCardService.findByStatus(status);
		if (!travelCard.isEmpty()) {
			return new ResponseEntity<>((Collection<TravelCard>) travelCard, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the travel card.
	 *
	 * @param travelCardRequestResource the travel card request resource
	 * @return the response entity
	 */
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addTravelCard(@Valid @RequestBody TravelCardRequestResource travelCardRequestResource) {
		String travelCardId = travelCardService.saveTravelCard(travelCardRequestResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), travelCardId);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update travel card.
	 *
	 * @param id the id
	 * @param travelCardRequestResource the travel card request resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateTravelCard(@PathVariable(value = "id", required = true) String id,
			@Valid @RequestBody TravelCardRequestResource travelCardRequestResource) {
		TravelCard travelCard = travelCardService.updateTravelCard(id, travelCardRequestResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), travelCard);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete travel card.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteTravelCard(@PathVariable(value = "id", required = true) String id) {
		String message = travelCardService.deleteTravelCard(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
