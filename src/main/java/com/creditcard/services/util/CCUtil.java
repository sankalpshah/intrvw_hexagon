package com.creditcard.services.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.creditcard.services.model.CardTypeNotValid;
import com.creditcard.services.model.CreditCardTypes;

public class CCUtil {
	
	public static final int NUMBER_OF_THREADS = 10;

	private CCUtil()
	{
		
	}
	
	public static String getStartDigits(String cardType) throws CardTypeNotValid{
		   try{
			   CreditCardTypes ccprovider = CreditCardTypes.valueOf(cardType.toUpperCase());
			   return String.valueOf(ccprovider.gettStartDigits());
		   }catch(Exception e){
			   throw new CardTypeNotValid();
		   }
	}
	
	public static Date getTodayDate() throws ParseException{
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		return formatter.parse(formatter.format(today));
	}
}
