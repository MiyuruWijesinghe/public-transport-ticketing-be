package com.csse.publictransport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.csse.publictransport.model.BusTour;
import com.csse.publictransport.resource.BusTourResource;

@Service
public interface BusTourService {

	public List<BusTour> findAll();
	
	
	public Optional<BusTour> findById(String id);
	
	
	public List<BusTour> findByBusId(String busId);
	
	
	public String saveBusTour(BusTourResource busTourResource);
	
	
	public BusTour updateBusTour(String id, BusTourResource busTourResource);
	
	
	public String deleteBusTour(String id);
	
}
