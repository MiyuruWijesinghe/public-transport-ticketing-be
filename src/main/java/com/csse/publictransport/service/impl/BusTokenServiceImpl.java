package com.csse.publictransport.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csse.publictransport.enums.CommonStatus;
import com.csse.publictransport.exception.NoRecordFoundException;
import com.csse.publictransport.model.BusToken;
import com.csse.publictransport.model.PublicTransportAccount;
import com.csse.publictransport.model.Users;
import com.csse.publictransport.repository.BusTokenRepository;
import com.csse.publictransport.repository.PublicTransportAccountRepository;
import com.csse.publictransport.repository.UserRepository;
import com.csse.publictransport.resource.BusTokenRequestResource;
import com.csse.publictransport.security.jwt.AuthTokenFilter;
import com.csse.publictransport.service.CommonService;
import com.csse.publictransport.service.BusTokenService;

/**
 * Bus Token Service Impl
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   01-05-2021   MenukaJ   IT19004914     Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class BusTokenServiceImpl implements BusTokenService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private PublicTransportAccountRepository publicTransportAccountRepository;
	
	@Autowired
	private BusTokenRepository busTokenRepository;
	
	private static final String RECORD_NOT_FOUND = "common.record-not-found";

	@Override
	public List<BusToken> findAll() {
		return busTokenRepository.findAll();
	}

	@Override
	public Optional<BusToken> findById(String id) {
		Optional<BusToken> busToken = busTokenRepository.findById(id);
		if (busToken.isPresent()) {
			return Optional.ofNullable(busToken.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<BusToken> findByStatus(String status) {
		return busTokenRepository.findByStatus(status);
	}

	@Override
	public String saveBusToken(BusTokenRequestResource busTokenRequestResource) {
		
		Optional<PublicTransportAccount> isPresentPublicTransportAccount = publicTransportAccountRepository.findById(busTokenRequestResource.getAccountId());
		if (!isPresentPublicTransportAccount.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("account-id.invalid"));
		}
		
		BusToken busToken = new BusToken();
		busToken.setPublicTransportAccount(isPresentPublicTransportAccount.get());
		busToken.setQrcode(busTokenRequestResource.getQrCode());
        busToken.setStatus(busTokenRequestResource.getStatus());
        busToken.setModifiedUser(authTokenFilter.getUsername());
        busToken.setModifiedDate(commonService.formatDate(new Date()));
        
        busToken = busTokenRepository.save(busToken);
        return busToken.getId();
	}

	@Override
	public BusToken updateBusToken(String id, BusTokenRequestResource busTokenRequestResource) {
		
		Optional<BusToken> isPresentBusToken = busTokenRepository.findById(id);
		if (!isPresentBusToken.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty(RECORD_NOT_FOUND));
		}
		
		BusToken busToken = isPresentBusToken.get();
        busToken.setStatus(busTokenRequestResource.getStatus());
        busToken.setModifiedUser(authTokenFilter.getUsername());
        busToken.setModifiedDate(commonService.formatDate(new Date()));
        
        return busTokenRepository.save(busToken);
	}

	@Override
	public String deleteBusToken(String id) {
		Optional<BusToken> isPresentBusToken = busTokenRepository.findById(id);
		if (!isPresentBusToken.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty(RECORD_NOT_FOUND));
		}
		
		busTokenRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

	@Override
	public Boolean validateBusToken(String qrCode) {
		Optional<BusToken> isPresentBusToken = busTokenRepository.findByqrcodeAndStatus(qrCode, CommonStatus.ACTIVE.toString());
		if (!isPresentBusToken.isPresent())
			return true;
		return false;
	}

}
