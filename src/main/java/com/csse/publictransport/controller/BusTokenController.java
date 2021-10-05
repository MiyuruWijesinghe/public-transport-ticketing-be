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

import com.csse.publictransport.model.BusToken;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.resource.BusTokenRequestResource;
import com.csse.publictransport.service.BusTokenService;


/**
 * Bus Token Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MenukaJ   IT19004914     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/bus-token")
@CrossOrigin(origins = "*")
public class BusTokenController {

	@Autowired
	private Environment environment;
	

	@Autowired
	private BusTokenService busTokenService;



	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllBusTokens() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<BusToken> busToken = busTokenService.findAll();
		if (!busToken.isEmpty()) {
			return new ResponseEntity<>((Collection<BusToken>) busToken, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getBusTokenById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<BusToken> isPresentBusToken = busTokenService.findById(id);
		if (isPresentBusToken.isPresent()) {
			return new ResponseEntity<>(isPresentBusToken, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping(value = "/qr-code/{qrcode}")
	public ResponseEntity<Object> getBusTokenByVehicleNo(@PathVariable(value = "qrCode", required = true) String qrCode) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		if (busTokenService.validateBusToken(qrCode)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	

	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getBusTokenByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<BusToken> busToken = busTokenService.findByStatus(status);
		if (!busToken.isEmpty()) {
			return new ResponseEntity<>((Collection<BusToken>) busToken, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	

	@PostMapping(value = "/save")
	public ResponseEntity<Object> addBusToken(@Valid @RequestBody BusTokenRequestResource busTokenRequestResource) {
		String busTokenId = busTokenService.saveBusToken(busTokenRequestResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), busTokenId);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateBusToken(@PathVariable(value = "id", required = true) String id,
			@Valid @RequestBody BusTokenRequestResource busTokenRequestResource) {
		BusToken busToken = busTokenService.updateBusToken(id, busTokenRequestResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), busToken);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteBusToken(@PathVariable(value = "id", required = true) String id) {
		String message = busTokenService.deleteBusToken(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
