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

import com.csse.publictransport.model.Bus;
import com.csse.publictransport.resource.BusResource;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.service.BusService;

/**
 * Bus Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/bus")
@CrossOrigin(origins = "*")
public class BusController {

	private static final Logger logger = LoggerFactory.getLogger(BusController.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BusService busService;

	
	/**
	 * Gets the all buses.
	 *
	 * @return the all buses
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllBuses() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Bus> bus = busService.findAll();
		if (!bus.isEmpty()) {
			return new ResponseEntity<>((Collection<Bus>) bus, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the bus by id.
	 *
	 * @param id - the id
	 * @return the bus by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getBusById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Bus> isPresentBus = busService.findById(id);
		if (isPresentBus.isPresent()) {
			return new ResponseEntity<>(isPresentBus, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the bus by vehicle no.
	 *
	 * @param vehicleNo - the vehicle no
	 * @return the bus by vehicle no
	 */
	@GetMapping(value = "/vehicleNo/{vehicleNo}")
	public ResponseEntity<Object> getBusByVehicleNo(@PathVariable(value = "vehicleNo", required = true) String vehicleNo) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Bus> isPresentBus = busService.findByVehicleNo(vehicleNo);
		if (isPresentBus.isPresent()) {
			return new ResponseEntity<>(isPresentBus, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the bus by status.
	 *
	 * @param status - the status
	 * @return the bus by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getBusByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Bus> bus = busService.findByStatus(status);
		if (!bus.isEmpty()) {
			return new ResponseEntity<>((Collection<Bus>) bus, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the bus.
	 *
	 * @param busAddResource - the bus add resource
	 * @return the response entity
	 */
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addBus(@Valid @RequestBody BusResource busAddResource) {
		String busId = busService.saveBus(busAddResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), busId);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update bus.
	 *
	 * @param id - the id
	 * @param busResource - the bus resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateBus(@PathVariable(value = "id", required = true) String id,
			@Valid @RequestBody BusResource busResource) {
		Bus bus = busService.updateBus(id, busResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), bus);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete bus.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteBus(@PathVariable(value = "id", required = true) String id) {
		String message = busService.deleteBus(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
