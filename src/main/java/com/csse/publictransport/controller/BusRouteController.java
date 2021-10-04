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

import com.csse.publictransport.model.BusRoute;
import com.csse.publictransport.resource.BusRouteResource;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.service.BusRouteService;

@RestController
@RequestMapping(value = "/bus-route")
@CrossOrigin(origins = "*")
public class BusRouteController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private BusRouteService busRouteService;

	
	
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllBusRoutes() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<BusRoute> busRoute = busRouteService.findAll();
		if (!busRoute.isEmpty()) {
			return new ResponseEntity<>((Collection<BusRoute>) busRoute, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getBusRouteById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<BusRoute> isPresentBusRoute = busRouteService.findById(id);
		if (isPresentBusRoute.isPresent()) {
			return new ResponseEntity<>(isPresentBusRoute, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	@GetMapping(value = "/routeNo/{routeNo}")
	public ResponseEntity<Object> getBusRouteByRouteNo(@PathVariable(value = "routeNo", required = true) String routeNo) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<BusRoute> isPresentBusRoute = busRouteService.findByRouteNo(routeNo);
		if (isPresentBusRoute.isPresent()) {
			return new ResponseEntity<>(isPresentBusRoute, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getBusRouteByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<BusRoute> busRoute = busRouteService.findByStatus(status);
		if (!busRoute.isEmpty()) {
			return new ResponseEntity<>((Collection<BusRoute>) busRoute, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addBusRoute(@Valid @RequestBody BusRouteResource busRouteAddResource) {
		String busRouteId = busRouteService.saveBusRoute(busRouteAddResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), busRouteId);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateBusRoute(@PathVariable(value = "id", required = true) String id,
			@Valid @RequestBody BusRouteResource busRouteResource) {
		BusRoute busRoute = busRouteService.updateBusRoute(id, busRouteResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), busRoute);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteBusRoute(@PathVariable(value = "id", required = true) String id) {
		String message = busRouteService.deleteBusRoute(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
