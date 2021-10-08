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

import com.csse.publictransport.model.Roles;
import com.csse.publictransport.resource.RolesAddResource;
import com.csse.publictransport.resource.RolesUpdateResource;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.service.RolesService;

/**
 * Roles Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/roles")
@CrossOrigin(origins = "*")
public class RolesController {
	
	private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

	@Autowired
	private Environment environment;
	
	@Autowired
	private RolesService rolesService;

	
	/**
	 * Gets the all roles.
	 *
	 * @return the all roles
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllRoles() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Roles> roles = rolesService.findAll();
		if (!roles.isEmpty()) {
			return new ResponseEntity<>((Collection<Roles>) roles, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the role by id.
	 *
	 * @param id - the id
	 * @return the role by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getRoleById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Roles> isPresentRoles = rolesService.findById(id);
		if (isPresentRoles.isPresent()) {
			return new ResponseEntity<>(isPresentRoles, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the role by name.
	 *
	 * @param name - the name
	 * @return the role by name
	 */
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getRoleByName(@PathVariable(value = "name", required = true) String name) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Roles> isPresentRoles = rolesService.findByName(name);
		if (isPresentRoles.isPresent()) {
			return new ResponseEntity<>(isPresentRoles, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the role.
	 *
	 * @param rolesAddResource - the roles add resource
	 * @return the response entity
	 */
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addRole(@Valid @RequestBody RolesAddResource rolesAddResource) {
		String rolesId = rolesService.saveRole(rolesAddResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), rolesId);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update role.
	 *
	 * @param id - the id
	 * @param rolesUpdateResource - the roles update resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateRole(@PathVariable(value = "id", required = true) String id,
			@Valid @RequestBody RolesUpdateResource rolesUpdateResource) {
		Roles roles = rolesService.updateRole(id, rolesUpdateResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), roles);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete role.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteRole(@PathVariable(value = "id", required = true) String id) {
		String message = rolesService.deleteRole(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
}