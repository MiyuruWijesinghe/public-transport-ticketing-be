package com.csse.publictransport.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PublicTransportAccountResource {

	@NotBlank(message = "{common.not-null}")
	private String passengerId;
	
	@NotBlank(message = "{common.not-null}")
	private String accountNo;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|CREDIT|DEBIT",message="{transactionType.pattern}")
	private String transactionType;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|\\d{1,20}\\.\\d{1,2}$",message="{common-amount.pattern}")
	private String amount;

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
