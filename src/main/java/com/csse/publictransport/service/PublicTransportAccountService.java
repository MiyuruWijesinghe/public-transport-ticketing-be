package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.csse.publictransport.model.PublicTransportAccount;
import com.csse.publictransport.resource.PublicTransportAccountAddResource;
import com.csse.publictransport.resource.PublicTransportAccountResource;
import com.csse.publictransport.resource.PublicTransportAccountUpdateResource;

@Service
public interface PublicTransportAccountService {

	public List<PublicTransportAccount> findAll();
	
	public Optional<PublicTransportAccount> findById(String id);
	
	public Optional<PublicTransportAccount> findByAccountNo(String accountNo);
	
	public Optional<PublicTransportAccount> findByUserId(String userId);
	
	public List<PublicTransportAccount> findByStatus(String status);
	
	public String savePublicTransportAccount(PublicTransportAccountAddResource publicTransportAccountAddResource);
	
	public PublicTransportAccount updatePublicTransportAccount(String id, PublicTransportAccountUpdateResource publicTransportAccountUpdateResource);
	
	public PublicTransportAccount rechargeOrWithdrawPublicTransportAccount(PublicTransportAccountResource publicTransportAccountResource);

	public String deletePublicTransportAccount(String id);
}
