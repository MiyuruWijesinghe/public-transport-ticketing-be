package com.csse.publictransport.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.csse.publictransport.model.BusTour;
import com.csse.publictransport.resource.BusTourResource;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.service.BusTourService;

/**
 * Bus Tour Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/bus-tour")
@CrossOrigin(origins = "*")
public class BusTourController {
	
	private static final Logger logger = LoggerFactory.getLogger(BusTourController.class);

	@Autowired
	private Environment environment;
	
	@Autowired
	private BusTourService busTourService;

	
	/**
	 * Gets the all bus tours.
	 *
	 * @return the all bus tours
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllBusTours() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<BusTour> busTour = busTourService.findAll();
		if (!busTour.isEmpty()) {
			return new ResponseEntity<>((Collection<BusTour>) busTour, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the bus tour by id.
	 *
	 * @param id - the id
	 * @return the bus tour by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getBusTourById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<BusTour> isPresentBusTour = busTourService.findById(id);
		if (isPresentBusTour.isPresent()) {
			return new ResponseEntity<>(isPresentBusTour, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the bus tours by bus id.
	 *
	 * @param busId - the bus id
	 * @return the bus tours by bus id
	 */
	@GetMapping(value = "/bus/{busId}")
	public ResponseEntity<Object> getBusToursByBusId(@PathVariable(value = "busId", required = true) String busId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<BusTour> busTour = busTourService.findByBusId(busId);
		if (!busTour.isEmpty()) {
			return new ResponseEntity<>((Collection<BusTour>) busTour, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the bus tour.
	 *
	 * @param busTourAddResource - the bus tour add resource
	 * @return the response entity
	 */
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addBusTour(@Valid @RequestBody BusTourResource busTourAddResource) {
		String busTourId = busTourService.saveBusTour(busTourAddResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), busTourId);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update bus tour.
	 *
	 * @param id - the id
	 * @param busTourResource - the bus tour resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateBusTour(@PathVariable(value = "id", required = true) String id,
			@Valid @RequestBody BusTourResource busTourResource) {
		BusTour busTour = busTourService.updateBusTour(id, busTourResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), busTour);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete bus tour.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteBusTour(@PathVariable(value = "id", required = true) String id) {
		String message = busTourService.deleteBusTour(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
