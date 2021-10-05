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
import com.csse.publictransport.model.Inspection;
import com.csse.publictransport.resource.InspectionResource;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.service.InspectionService;

/**
 * Inspection Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/inspection")
@CrossOrigin(origins = "*")
public class InspectionController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private InspectionService inspectionService;
	
	
	/**
	 * Gets the all inspections.
	 *
	 * @return the all inspections
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllInspections() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Inspection> inspection = inspectionService.findAll();
		if (!inspection.isEmpty()) {
			return new ResponseEntity<>((Collection<Inspection>) inspection, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
		
	
	/**
	 * Gets the inspection by id.
	 *
	 * @param id - the id
	 * @return the inspection by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getInspectionById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Inspection> isPresentInspection = inspectionService.findById(id);
		if (isPresentInspection.isPresent()) {
			return new ResponseEntity<>(isPresentInspection, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}

	
	/**
	 * Gets the inspection by user id.
	 *
	 * @param userId - the user id
	 * @return the inspection by user id
	 */
	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<Object> getInspectionByUserId(@PathVariable(value = "userId", required = true) String userId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Inspection> inspection = inspectionService.findByUserId(userId);
		if (!inspection.isEmpty()) {
			return new ResponseEntity<>((Collection<Inspection>) inspection, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the inspection by route id.
	 *
	 * @param routeId - the route id
	 * @return the inspection by route id
	 */
	@GetMapping(value = "/route/{routeId}")
	public ResponseEntity<Object> getInspectionByRouteId(@PathVariable(value = "routeId", required = true) String routeId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Inspection> inspection = inspectionService.findByBusRouteId(routeId);
		if (!inspection.isEmpty()) {
			return new ResponseEntity<>((Collection<Inspection>) inspection, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the inspection by user id and route id.
	 *
	 * @param userId - the user id
	 * @param routeId - the route id
	 * @return the inspection by user id and route id
	 */
	@GetMapping(value = "/user/{userId}/route/{routeId}")
	public ResponseEntity<Object> getInspectionByUserIdAndRouteId(@PathVariable(value = "userId", required = true) String userId,
			@PathVariable(value = "routeId", required = true) String routeId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Inspection> isPresentInspection = inspectionService.findByUserIdAndBusRouteId(userId, routeId);
		if (isPresentInspection.isPresent()) {
			return new ResponseEntity<>(isPresentInspection, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the inspection.
	 *
	 * @param inspectionResource - the inspection resource
	 * @return the response entity
	 */
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addInspection(@Valid @RequestBody InspectionResource inspectionResource) {
		String inspectionId = inspectionService.saveInspection(inspectionResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), inspectionId);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update inspection.
	 *
	 * @param id - the id
	 * @param inspectionResource - the inspection resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateInspection(@PathVariable(value = "id", required = true) String id,
			@Valid @RequestBody InspectionResource inspectionResource) {
		Inspection inspection = inspectionService.updateInspection(id, inspectionResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), inspection);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete inspection.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteInspection(@PathVariable(value = "id", required = true) String id) {
		String message = inspectionService.deleteInspection(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
}
