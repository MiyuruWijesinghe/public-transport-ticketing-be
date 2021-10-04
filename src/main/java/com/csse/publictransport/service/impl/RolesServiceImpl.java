package com.csse.publictransport.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csse.publictransport.enums.CommonStatus;
import com.csse.publictransport.exception.NoRecordFoundException;
import com.csse.publictransport.exception.ValidateRecordException;
import com.csse.publictransport.model.Roles;
import com.csse.publictransport.repository.RolesRepository;
import com.csse.publictransport.resource.RolesAddResource;
import com.csse.publictransport.resource.RolesUpdateResource;
import com.csse.publictransport.service.RolesService;


/**
 * Roles Service Implementation
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MENUKAJ   IT19004914     Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class RolesServiceImpl implements RolesService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private RolesRepository rolesRepository;

	@Override
	public List<Roles> findAll() {
		return rolesRepository.findAll();
	}

	@Override
	public Optional<Roles> findById(String id) {
		Optional<Roles> roles = rolesRepository.findById(id);
		if (roles.isPresent()) {
			return Optional.ofNullable(roles.get());
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<Roles> findByName(String name) {
		Optional<Roles> roles = rolesRepository.findByName(name);
		if (roles.isPresent()) {
			return Optional.ofNullable(roles.get());
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public String saveRole(RolesAddResource rolesAddResource) {
		Roles roles = new Roles();
		
		Optional<Roles> isPresentRole = rolesRepository.findByName("ROLE_" + rolesAddResource.getName());
        if (isPresentRole.isPresent()) {
        	throw new ValidateRecordException(environment.getProperty("role.duplicate"), "message");
		}
		
		roles.setName(rolesAddResource.getName());
		roles.setStatus(CommonStatus.ACTIVE.toString());
		rolesRepository.save(roles);
		return roles.getId();
	}

	@Override
	public Roles updateRole(String id, RolesUpdateResource rolesUpdateResource) {
		
		Optional<Roles> isPresentRoles = rolesRepository.findById(id);
		if (!isPresentRoles.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		Roles roles = isPresentRoles.get();
		roles.setStatus(rolesUpdateResource.getStatus());
		rolesRepository.save(roles);
		return roles;
	}
	
	@Override
	public String deleteRole(String id) {
		Optional<Roles> isPresentRoles = rolesRepository.findById(id);
		if (!isPresentRoles.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		rolesRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

}