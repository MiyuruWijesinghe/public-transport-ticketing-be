package com.csse.publictransport.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class BusTokenRequestResource {
	
	@Pattern(regexp = "^$|[0-9]+", message = "{common-numeric.pattern}")
	private String accountId;

	@NotBlank(message = "{common.not-null}")
    private String qrCode;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|ACTIVE|INACTIVE",message="{common-status.pattern}")
	private String status;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
