package com.creditcard.services.model;


public enum CreditCardTypes {	
	VISA(4),
	MASTERCARD(5),
	AMEX(37),
	DISCOVERCARD(6);
	
	private int startDigits;
	
	private CreditCardTypes(int startNumber){
		this.startDigits = startNumber;
	}
	
	public int gettStartDigits(){
		return startDigits;
	}
}