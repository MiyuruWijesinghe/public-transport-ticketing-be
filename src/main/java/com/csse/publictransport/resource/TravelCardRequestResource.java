package com.csse.publictransport.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class TravelCardRequestResource {
	
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
	private String userId;

//	@NotBlank(message = "{common.not-null}")
//	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
    private String qrCode;
    
    @NotBlank(message = "{common.not-null}")
    @Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
    private String creditBalance;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|ACTIVE|INACTIVE",message="{common-status.pattern}")
	private String status;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(String creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
