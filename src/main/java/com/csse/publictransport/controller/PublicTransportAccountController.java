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

import com.csse.publictransport.model.PublicTransportAccount;
import com.csse.publictransport.resource.PublicTransportAccountAddResource;
import com.csse.publictransport.resource.PublicTransportAccountResource;
import com.csse.publictransport.resource.PublicTransportAccountUpdateResource;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.service.PublicTransportAccountService;

@RestController
@RequestMapping(value = "/account")
@CrossOrigin(origins = "*")
public class PublicTransportAccountController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private PublicTransportAccountService publicTransportAccountService;

	
	
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllPublicTransportAccounts() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<PublicTransportAccount> publicTransportAccount = publicTransportAccountService.findAll();
		if (!publicTransportAccount.isEmpty()) {
			return new ResponseEntity<>((Collection<PublicTransportAccount>) publicTransportAccount, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getPublicTransportAccountById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<PublicTransportAccount> publicTransportAccount = publicTransportAccountService.findById(id);
		if (publicTransportAccount.isPresent()) {
			return new ResponseEntity<>(publicTransportAccount, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}

	
	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<Object> getPublicTransportAccountByUserId(@PathVariable(value = "userId", required = true) String userId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<PublicTransportAccount> publicTransportAccount = publicTransportAccountService.findByUserId(userId);
		if (publicTransportAccount.isPresent()) {
			return new ResponseEntity<>(publicTransportAccount, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/account/{accountNo}")
	public ResponseEntity<Object> getPublicTransportAccountByAccountNo(@PathVariable(value = "accountNo", required = true) String accountNo) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<PublicTransportAccount> publicTransportAccount = publicTransportAccountService.findByAccountNo(accountNo);
		if (publicTransportAccount.isPresent()) {
			return new ResponseEntity<>(publicTransportAccount, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getPublicTransportAccountsByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<PublicTransportAccount> publicTransportAccount = publicTransportAccountService.findByStatus(status);
		if (!publicTransportAccount.isEmpty()) {
			return new ResponseEntity<>((Collection<PublicTransportAccount>) publicTransportAccount, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addPublicTransportAccount(@Valid @RequestBody PublicTransportAccountAddResource publicTransportAccountAddResource) {
		String accountId = publicTransportAccountService.savePublicTransportAccount(publicTransportAccountAddResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), accountId);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updatePublicTransportAccount(@PathVariable(value = "id", required = true) String id,
			@Valid @RequestBody PublicTransportAccountUpdateResource publicTransportAccountUpdateResource) {
		PublicTransportAccount publicTransportAccount = publicTransportAccountService.updatePublicTransportAccount(id, publicTransportAccountUpdateResource);		
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), publicTransportAccount);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/tranaction")
	public ResponseEntity<Object> tranactionPublicTransportAccount(@Valid @RequestBody PublicTransportAccountResource publicTransportAccountResource) {
		PublicTransportAccount publicTransportAccount = publicTransportAccountService.rechargeOrWithdrawPublicTransportAccount(publicTransportAccountResource);		
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), publicTransportAccount);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletePublicTransportAccount(@PathVariable(value = "id", required = true) String id) {
		String message = publicTransportAccountService.deletePublicTransportAccount(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
