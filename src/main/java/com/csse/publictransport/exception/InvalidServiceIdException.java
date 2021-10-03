package com.csse.publictransport.exception;

import com.csse.publictransport.enums.ServiceEntity;

public class InvalidServiceIdException extends RuntimeException {

	private static final long serialVersionUID = -5687046029326193822L;
	
	private final ServiceEntity serviceEntity;
	
	public InvalidServiceIdException(String exception,ServiceEntity serviceEntity) {
		super(exception);
		this.serviceEntity=serviceEntity;
	}
	
	public ServiceEntity getServiceEntity() {
		return serviceEntity;
	}
}
