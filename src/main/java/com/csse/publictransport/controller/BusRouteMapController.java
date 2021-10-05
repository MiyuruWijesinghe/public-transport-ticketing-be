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

import com.csse.publictransport.model.BusRouteMap;
import com.csse.publictransport.resource.BusRouteMapResource;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.service.BusRouteMapService;

/**
 * Bus Route Map Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/bus-route-map")
@CrossOrigin(origins = "*")
public class BusRouteMapController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private BusRouteMapService busRouteMapService;

	
	/**
	 * Gets the all bus route maps.
	 *
	 * @return the all bus route maps
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllBusRouteMaps() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<BusRouteMap> busRouteMap = busRouteMapService.findAll();
		if (!busRouteMap.isEmpty()) {
			return new ResponseEntity<>((Collection<BusRouteMap>) busRouteMap, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the bus route map by id.
	 *
	 * @param id - the id
	 * @return the bus route map by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getBusRouteMapById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<BusRouteMap> isPresentBusRouteMap = busRouteMapService.findById(id);
		if (isPresentBusRouteMap.isPresent()) {
			return new ResponseEntity<>(isPresentBusRouteMap, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the bus route map by status.
	 *
	 * @param status - the status
	 * @return the bus route map by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getBusRouteMapByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<BusRouteMap> busRouteMap = busRouteMapService.findByStatus(status);
		if (!busRouteMap.isEmpty()) {
			return new ResponseEntity<>((Collection<BusRouteMap>) busRouteMap, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the bus route map by bus id.
	 *
	 * @param busId - the bus id
	 * @return the bus route map by bus id
	 */
	@GetMapping(value = "/bus/{busId}")
	public ResponseEntity<Object> getBusRouteMapByBusId(@PathVariable(value = "busId", required = true) String busId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<BusRouteMap> busRouteMap = busRouteMapService.findByBusId(busId);
		if (!busRouteMap.isEmpty()) {
			return new ResponseEntity<>((Collection<BusRouteMap>) busRouteMap, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the bus route map by route id.
	 *
	 * @param routeId - the route id
	 * @return the bus route map by route id
	 */
	@GetMapping(value = "/route/{routeId}")
	public ResponseEntity<Object> getBusRouteMapByRouteId(@PathVariable(value = "routeId", required = true) String routeId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<BusRouteMap> busRouteMap = busRouteMapService.findByRouteId(routeId);
		if (!busRouteMap.isEmpty()) {
			return new ResponseEntity<>((Collection<BusRouteMap>) busRouteMap, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the bus route map by bus id and route id.
	 *
	 * @param busId - the bus id
	 * @param routeId - the route id
	 * @return the bus route map by bus id and route id
	 */
	@GetMapping(value = "/bus/{busId}/route/{routeId}")
	public ResponseEntity<Object> getBusRouteMapByBusIdAndRouteId(@PathVariable(value = "busId", required = true) String busId,
			@PathVariable(value = "routeId", required = true) String routeId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<BusRouteMap> isPresentBusRouteMap = busRouteMapService.findByBusIdAndBusRouteId(busId, routeId);
		if (isPresentBusRouteMap.isPresent()) {
			return new ResponseEntity<>(isPresentBusRouteMap, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the bus route map.
	 *
	 * @param busRouteMapAddResource - the bus route map add resource
	 * @return the response entity
	 */
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addBusRouteMap(@Valid @RequestBody BusRouteMapResource busRouteMapAddResource) {
		String busRouteMapId = busRouteMapService.saveBusRouteMap(busRouteMapAddResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), busRouteMapId);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update bus route map.
	 *
	 * @param id - the id
	 * @param busRouteMapResource - the bus route map resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateBusRouteMap(@PathVariable(value = "id", required = true) String id,
			@Valid @RequestBody BusRouteMapResource busRouteMapResource) {
		BusRouteMap busRouteMap = busRouteMapService.updateBusRouteMap(id, busRouteMapResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), busRouteMap);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete bus route map.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteBusRouteMap(@PathVariable(value = "id", required = true) String id) {
		String message = busRouteMapService.deleteBusRouteMap(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
}
