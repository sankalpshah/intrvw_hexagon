package com.creditcard.services.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.creditcard.services.model.CreditCard;
import com.creditcard.services.util.CCUtil;

public class CreditCardProcessor {
	private static CreditCardProcessor processor = new CreditCardProcessor();
	
	private  ExecutorService exec;
	 
	private CreditCardProcessor()
	{
		this.exec = Executors.newFixedThreadPool(CCUtil.NUMBER_OF_THREADS);
	}
	
	public static CreditCardProcessor getInstance(){
		return processor;
	}
	
	public synchronized List<CreditCard> processCreditCard(List<String> creditCardcList, String cardType) throws ExecutionException{
		 List<CreditCard> creditCards = new ArrayList<>();
		 List<Future<CreditCard>> futures = new ArrayList<>(creditCardcList.size());	 
		 for (String creditCardNumber : creditCardcList) {
		        futures.add(exec.submit(new ValidatorTask(creditCardNumber, cardType)));
		 }
	     for (Future<?> f : futures) {
	        try {
	        	Object obj = f.get();
	        		if(obj instanceof CreditCard)
	        				creditCards.add((CreditCard)obj);
			} catch (InterruptedException ie){
				Thread.currentThread().interrupt();
			}  // wait for a processor to complete
	     }
	     return creditCards;
	}
	
	

}
