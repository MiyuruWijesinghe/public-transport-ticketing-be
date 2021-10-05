package com.csse.publictransport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.publictransport.model.PublicTransportAccount;

@Repository
public interface PublicTransportAccountRepository extends MongoRepository<PublicTransportAccount, String> {

	public Optional<PublicTransportAccount> findByAccountNo(String accountNo);
	
	public Optional<PublicTransportAccount> findByUsersId(String userId);
	
	public List<PublicTransportAccount> findByStatus(String status);
	
	public Boolean existsByUsersId(String userId);
	
	public Boolean existsByAccountNo(String accountNo);
	
}
