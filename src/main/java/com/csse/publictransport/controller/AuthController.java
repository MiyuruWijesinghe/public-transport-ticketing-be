package com.csse.publictransport.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csse.publictransport.model.Users;
import com.csse.publictransport.resource.JwtResponseResource;
import com.csse.publictransport.resource.LoginRequestResource;
import com.csse.publictransport.resource.MessageResponseResource;
import com.csse.publictransport.resource.SignupRequestResource;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.service.AuthService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private Environment environment;
	
	@Autowired
	AuthService authService;
	

	/**
	 * Authenticate user.
	 *
	 * @param loginRequest the login request
	 * @return the response entity
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestResource loginRequest) {
		JwtResponseResource jwtResponseResource = authService.authenticateUser(loginRequest);
		return new ResponseEntity<>(jwtResponseResource,HttpStatus.CREATED);
	}

	/**
	 * Register user.
	 *
	 * @param signUpRequest the sign up request
	 * @return the response entity
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestResource signUpRequest) {
		Users user =  authService.registerUser(signUpRequest);
		MessageResponseResource responseMessage = new MessageResponseResource(environment.getProperty("registered.success"), user.getId());
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	
	
	/**
	 * Gets the user by user name.
	 *
	 * @param username - the username
	 * @return the user by user name
	 */
	@GetMapping(value = "/username/{username}")
	public ResponseEntity<Object> getUserByUserName(@PathVariable(value = "username", required = true) String username) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Users> isPresentUsers = authService.findByUserName(username);
		if (isPresentUsers.isPresent()) {
			return new ResponseEntity<>(isPresentUsers, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
}
