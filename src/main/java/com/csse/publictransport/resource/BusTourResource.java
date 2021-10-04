package com.csse.publictransport.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BusTourResource {

	@NotBlank(message = "{common.not-null}")
	private String busId;

	@NotBlank(message = "{common.not-null}")
	private String busRouteId;

	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|(\\d{4})-(\\d{2})-(\\d{2})$", message = "{common.invalid-date-pattern}")
	private String date;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|(\\d{2}):(\\d{2}):(\\d{2})$", message = "{common.invalid-time-pattern}")
	private String startTime;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|(\\d{2}):(\\d{2}):(\\d{2})$", message = "{common.invalid-time-pattern}")
	private String endTime;
	
	@NotBlank(message = "{common.not-null}")
	private String direction;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
	private String tourRevenue;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
	private String totalPassengers;

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getBusRouteId() {
		return busRouteId;
	}

	public void setBusRouteId(String busRouteId) {
		this.busRouteId = busRouteId;
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

	public String getTourRevenue() {
		return tourRevenue;
	}

	public void setTourRevenue(String tourRevenue) {
		this.tourRevenue = tourRevenue;
	}

	public String getTotalPassengers() {
		return totalPassengers;
	}

	public void setTotalPassengers(String totalPassengers) {
		this.totalPassengers = totalPassengers;
	}
	
}
