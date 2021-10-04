package com.csse.publictransport.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public interface CommonService {

	public String formatDate(Date date);
	
}
