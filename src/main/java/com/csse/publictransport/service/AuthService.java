package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.csse.publictransport.model.Users;
import com.csse.publictransport.resource.JwtResponseResource;
import com.csse.publictransport.resource.LoginRequestResource;
import com.csse.publictransport.resource.SignupRequestResource;

/**
 * Auth Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface AuthService {

	/**
	 * Authenticate user.
	 *
	 * @param loginRequest the login request
	 * @return the jwt response resource
	 */
	public JwtResponseResource authenticateUser(LoginRequestResource loginRequest);

	/**
	 * Register user.
	 *
	 * @param signUpRequest the sign up request
	 * @return the users
	 */
	public Users registerUser(SignupRequestResource signUpRequest);
	
	public Optional<Users> findByUserName(String username);
	
	public List<Users> findAll();
}
