package com.creditcard.services.helper;

import java.util.Date;
import java.util.concurrent.Callable;

import com.creditcard.services.model.CardTypeNotValid;
import com.creditcard.services.model.CreditCard;
import com.creditcard.services.util.CCUtil;

public class ValidatorTask implements Callable<CreditCard> {

	private String creditCardNumber;
	private String cardType;

	public ValidatorTask(String creditCardNumber, String cardType){
		this.creditCardNumber = creditCardNumber;
		this.cardType = cardType;
		
	}
	
	@Override
	public CreditCard call() throws Exception {
		boolean bvalid = false;
		try{
			bvalid = validate(creditCardNumber, cardType);
		}catch(CardTypeNotValid ce){
			//logger - cardNumber and cardType
		}
		if(bvalid){
			return getCreditCard(creditCardNumber, cardType);
		}
		else
			return null;
	}
	
	private boolean validate(String cc, String cardType) throws CardTypeNotValid{
		return cc.startsWith(CCUtil.getStartDigits(cardType));
	}
	
	private CreditCard getCreditCard(String creditcard, String type){
		return  new CreditCard(creditcard, type,new Date());		
	}
	
	

}
