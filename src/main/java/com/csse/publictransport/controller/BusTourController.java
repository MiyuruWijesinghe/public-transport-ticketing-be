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

import com.csse.publictransport.model.BusTour;
import com.csse.publictransport.resource.BusTourResource;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.service.BusTourService;

@RestController
@RequestMapping(value = "/bus-tour")
@CrossOrigin(origins = "*")
public class BusTourController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private BusTourService busTourService;

	
	
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
	
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addBusTour(@Valid @RequestBody BusTourResource busTourAddResource) {
		String busTourId = busTourService.saveBusTour(busTourAddResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), busTourId);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateBusTour(@PathVariable(value = "id", required = true) String id,
			@Valid @RequestBody BusTourResource busTourResource) {
		BusTour busTour = busTourService.updateBusTour(id, busTourResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), busTour);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteBusTour(@PathVariable(value = "id", required = true) String id) {
		String message = busTourService.deleteBusTour(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
