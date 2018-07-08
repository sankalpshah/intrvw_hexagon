package com.creditcard.services.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditCard {

	private String type;
	private String number;
	private Date expiryDate;
	
	public CreditCard(String type,String number,Date expiryDate ){
		this.type = type;
		this.number = number;
		this.expiryDate = expiryDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpiryDate() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(expiryDate);
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
