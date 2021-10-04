package com.csse.publictransport.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class InspectionResource {

	@NotBlank(message = "{common.not-null}")
	private String inspectorId;

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
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
	private String numOfInspections;

	public String getInspectorId() {
		return inspectorId;
	}

	public void setInspectorId(String inspectorId) {
		this.inspectorId = inspectorId;
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

	public String getNumOfInspections() {
		return numOfInspections;
	}

	public void setNumOfInspections(String numOfInspections) {
		this.numOfInspections = numOfInspections;
	}
	
}
