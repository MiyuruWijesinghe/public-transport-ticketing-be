package com.csse.publictransport.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BusTour")
public class BusTour {

	@Id
	private String id;
	
	private BusRouteMap busRouteMap;
	
	private String date;
	
	private String startTime;
	
	private String endTime;
	
	private String direction;
	
	private BigDecimal tourRevenue;
	
	private Long totalPassengers;
	
	private String createdUser;
	
	private String createdDate;
	
	private String modifiedUser;
	
	private String modifiedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public BusRouteMap getBusRouteMap() {
		return busRouteMap;
	}

	public void setBusRouteMap(BusRouteMap busRouteMap) {
		this.busRouteMap = busRouteMap;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getTourRevenue() {
		return tourRevenue;
	}

	public void setTourRevenue(BigDecimal tourRevenue) {
		this.tourRevenue = tourRevenue;
	}

	public Long getTotalPassengers() {
		return totalPassengers;
	}

	public void setTotalPassengers(Long totalPassengers) {
		this.totalPassengers = totalPassengers;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
