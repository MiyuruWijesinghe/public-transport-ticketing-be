package com.csse.publictransport.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csse.publictransport.exception.NoRecordFoundException;
import com.csse.publictransport.model.TravelCard;
import com.csse.publictransport.model.Users;
import com.csse.publictransport.repository.TravelCardRepository;
import com.csse.publictransport.repository.UserRepository;
import com.csse.publictransport.resource.TravelCardRequestResource;
import com.csse.publictransport.security.jwt.AuthTokenFilter;
import com.csse.publictransport.service.CommonService;
import com.csse.publictransport.service.TravelCardService;

/**
 * Travel Card Service Impl
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
public class TravelCardServiceImpl implements TravelCardService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TravelCardRepository travelCardRepository;
	
	private static final String RECORD_NOT_FOUND = "common.record-not-found";

	@Override
	public List<TravelCard> findAll() {
		return travelCardRepository.findAll();
	}

	@Override
	public Optional<TravelCard> findById(String id) {
		Optional<TravelCard> travelCard = travelCardRepository.findById(id);
		if (travelCard.isPresent()) {
			return Optional.ofNullable(travelCard.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<TravelCard> findByStatus(String status) {
		return travelCardRepository.findByStatus(status);
	}

	@Override
	public String saveTravelCard(TravelCardRequestResource travelCardRequestResource) {
		
		Optional<Users> isPresentUsers = userRepository.findById(travelCardRequestResource.getUserId());
		if (!isPresentUsers.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("user.invalid-value"));
		}
		
		TravelCard travelCard = new TravelCard();
		travelCard.setUsers(isPresentUsers.get());
		travelCard.setQrcode(travelCardRequestResource.getQrCode());
		travelCard.setCreditBalance(new BigDecimal(travelCardRequestResource.getCreditBalance()));
        travelCard.setStatus(travelCardRequestResource.getStatus());
        travelCard.setModifiedUser(authTokenFilter.getUsername());
        travelCard.setModifiedDate(commonService.formatDate(new Date()));
        
        travelCard = travelCardRepository.save(travelCard);
        return travelCard.getId();
	}

	@Override
	public TravelCard updateTravelCard(String id, TravelCardRequestResource travelCardRequestResource) {
		
		Optional<TravelCard> isPresentTravelCard = travelCardRepository.findById(id);
		if (!isPresentTravelCard.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty(RECORD_NOT_FOUND));
		}
		
		TravelCard travelCard = isPresentTravelCard.get();
		travelCard.setQrcode(travelCardRequestResource.getQrCode());
		travelCard.setCreditBalance(new BigDecimal(travelCardRequestResource.getCreditBalance()));
        travelCard.setStatus(travelCardRequestResource.getStatus());
        travelCard.setModifiedUser(authTokenFilter.getUsername());
        travelCard.setModifiedDate(commonService.formatDate(new Date()));
        
        return travelCardRepository.save(travelCard);
	}

	@Override
	public String deleteTravelCard(String id) {
		Optional<TravelCard> isPresentTravelCard = travelCardRepository.findById(id);
		if (!isPresentTravelCard.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty(RECORD_NOT_FOUND));
		}
		
		travelCardRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

}
