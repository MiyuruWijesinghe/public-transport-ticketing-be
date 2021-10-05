package com.csse.publictransport.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csse.publictransport.enums.CommonStatus;
import com.csse.publictransport.exception.ValidateRecordException;
import com.csse.publictransport.model.Roles;
import com.csse.publictransport.model.Users;
import com.csse.publictransport.repository.RolesRepository;
import com.csse.publictransport.repository.UserRepository;
import com.csse.publictransport.resource.JwtResponseResource;
import com.csse.publictransport.resource.LoginRequestResource;
import com.csse.publictransport.resource.SignupRequestResource;
import com.csse.publictransport.security.jwt.JwtUtils;
import com.csse.publictransport.service.AuthService;

/**
 * Auth Service Implementation
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RolesRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public JwtResponseResource authenticateUser(LoginRequestResource loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		String role = roles.get(0);
		return new JwtResponseResource(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 role);
	}

	@Override
	public Users registerUser(SignupRequestResource signUpRequest) {
		Set<Roles> roles = new HashSet<>();
		
		Users user = new Users();
		
		Optional<Users> isPresentUser = userRepository.findByUsername(signUpRequest.getUserName());
        if (isPresentUser.isPresent()) {
        	throw new ValidateRecordException(environment.getProperty("user.duplicate"), "message");
		}
        
        Optional<Roles> role = roleRepository.findByName(signUpRequest.getRoleName());
		if (!role.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("role.invalid-value"), "message");
		} else {
			roles.add(role.get());
		}
		
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setDob(signUpRequest.getDob());
		user.setNic(signUpRequest.getNic());
		user.setUsername(signUpRequest.getUserName());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setAddressLine1(signUpRequest.getAddressLine1());
		user.setAddressLine2(signUpRequest.getAddressLine2());
		user.setAddressLine3(signUpRequest.getAddressLine3());
		user.setEmail(signUpRequest.getEmail());
		user.setPhoneNumber(signUpRequest.getPhoneNumber());
		user.setStatus(CommonStatus.ACTIVE.toString());
		user.setRoles(roles);
		userRepository.save(user);
		return user;
		
	}
	
	@Override
	public Optional<Users> findByUserName(String username) {
		Optional<Users> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			return Optional.ofNullable(user.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Users> findAll() {
		return userRepository.findAll();
	}
	
}
