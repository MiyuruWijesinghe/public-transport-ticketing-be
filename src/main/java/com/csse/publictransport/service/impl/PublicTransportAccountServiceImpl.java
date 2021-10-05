package com.csse.publictransport.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csse.publictransport.enums.CommonStatus;
import com.csse.publictransport.enums.TransactionType;
import com.csse.publictransport.exception.NoRecordFoundException;
import com.csse.publictransport.exception.ValidateRecordException;
import com.csse.publictransport.model.PublicTransportAccount;
import com.csse.publictransport.model.Users;
import com.csse.publictransport.repository.PublicTransportAccountRepository;
import com.csse.publictransport.repository.UserRepository;
import com.csse.publictransport.resource.PublicTransportAccountAddResource;
import com.csse.publictransport.resource.PublicTransportAccountResource;
import com.csse.publictransport.resource.PublicTransportAccountUpdateResource;
import com.csse.publictransport.security.jwt.AuthTokenFilter;
import com.csse.publictransport.service.CommonService;
import com.csse.publictransport.service.PublicTransportAccountService;

/**
 * Public Transport Account Service Implementation
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
public class PublicTransportAccountServiceImpl implements PublicTransportAccountService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PublicTransportAccountRepository publicTransportAccountRepository;
	
	@Override
	public List<PublicTransportAccount> findAll() {
		return publicTransportAccountRepository.findAll();
	}

	@Override
	public Optional<PublicTransportAccount> findById(String id) {
		Optional<PublicTransportAccount> publicTransportAccount = publicTransportAccountRepository.findById(id);
		if (publicTransportAccount.isPresent()) {
			return Optional.ofNullable(publicTransportAccount.get());
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<PublicTransportAccount> findByAccountNo(String accountNo) {
		Optional<PublicTransportAccount> publicTransportAccount = publicTransportAccountRepository.findByAccountNo(accountNo);
		if (publicTransportAccount.isPresent()) {
			return Optional.ofNullable(publicTransportAccount.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<PublicTransportAccount> findByUserId(String userId) {
		Optional<PublicTransportAccount> publicTransportAccount = publicTransportAccountRepository.findByUsersId(userId);
		if (publicTransportAccount.isPresent()) {
			return Optional.ofNullable(publicTransportAccount.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<PublicTransportAccount> findByStatus(String status) {
		return publicTransportAccountRepository.findByStatus(status);
	}

	@Override
	public String savePublicTransportAccount(PublicTransportAccountAddResource publicTransportAccountAddResource) {
		PublicTransportAccount publicTransportAccount = new PublicTransportAccount();
		
        if (publicTransportAccountRepository.existsByUsersId(publicTransportAccountAddResource.getPassengerId())) {
        	throw new ValidateRecordException(environment.getProperty("account-user.duplicate"), "message");
		}
        
        String accountNo = generateAccountNo();
        
        if (publicTransportAccountRepository.existsByAccountNo(accountNo)) {
        	throw new ValidateRecordException(environment.getProperty("account-accNo.duplicate"), "message");
		}
        
        Optional<Users> users = userRepository.findByIdAndStatus(publicTransportAccountAddResource.getPassengerId(), CommonStatus.ACTIVE.toString());
		if (!users.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("user.invalid-value"), "message");
		} else {
			publicTransportAccount.setUsers(users.get());
		}
		
		publicTransportAccount.setAccountNo(accountNo);
		publicTransportAccount.setStatus(CommonStatus.ACTIVE.toString());
		publicTransportAccount.setAmount(new BigDecimal(publicTransportAccountAddResource.getAmount()));
		publicTransportAccount.setCreatedUser(authTokenFilter.getUsername());
		publicTransportAccount.setCreatedDate(commonService.formatDate(new Date()));
		publicTransportAccountRepository.save(publicTransportAccount);
		return publicTransportAccount.getId();
	}

	private String generateAccountNo() {
		String prefix = "AC202100";
		Random rand = new Random();
		int r1 = rand.nextInt(10);
	    return prefix + r1;
	}
	
	@Override
	public PublicTransportAccount updatePublicTransportAccount(String id, PublicTransportAccountUpdateResource publicTransportAccountUpdateResource) {
		Optional<PublicTransportAccount> isPresentPublicTransportAccount = publicTransportAccountRepository.findById(id);
		if (!isPresentPublicTransportAccount.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		PublicTransportAccount publicTransportAccount = isPresentPublicTransportAccount.get();
		publicTransportAccount.setStatus(publicTransportAccountUpdateResource.getStatus());
		publicTransportAccount.setModifiedUser(authTokenFilter.getUsername());
		publicTransportAccount.setModifiedDate(commonService.formatDate(new Date()));
		publicTransportAccountRepository.save(publicTransportAccount);
		return publicTransportAccount;
	}

	@Override
	public PublicTransportAccount rechargeOrWithdrawPublicTransportAccount(PublicTransportAccountResource publicTransportAccountResource) {
		Optional<PublicTransportAccount> isPresentPublicTransportAccount = publicTransportAccountRepository.findByAccountNo(publicTransportAccountResource.getAccountNo());
		if (!isPresentPublicTransportAccount.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		PublicTransportAccount publicTransportAccount = isPresentPublicTransportAccount.get();
		
		BigDecimal transactionAmount = new BigDecimal(publicTransportAccountResource.getAmount());
		BigDecimal currentBalance = publicTransportAccount.getAmount();
		BigDecimal newBalance = BigDecimal.ZERO;
		
		if (publicTransportAccountResource.getTransactionType().equalsIgnoreCase(TransactionType.CREDIT.toString())) {
			newBalance = currentBalance.add(transactionAmount);
		} else if (publicTransportAccountResource.getTransactionType().equalsIgnoreCase(TransactionType.DEBIT.toString())) {
			newBalance = currentBalance.subtract(transactionAmount);
		}
		
		publicTransportAccount.setAmount(newBalance);
		publicTransportAccount.setModifiedUser(authTokenFilter.getUsername());
		publicTransportAccount.setModifiedDate(commonService.formatDate(new Date()));
		publicTransportAccountRepository.save(publicTransportAccount);
		return publicTransportAccount;
	}

	@Override
	public String deletePublicTransportAccount(String id) {
		Optional<PublicTransportAccount> isPresentPublicTransportAccount = publicTransportAccountRepository.findById(id);
		if (!isPresentPublicTransportAccount.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		publicTransportAccountRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}
	
}
