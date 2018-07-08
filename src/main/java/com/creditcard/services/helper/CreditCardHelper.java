package com.creditcard.services.helper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.creditcard.services.model.CardTypeNotValid;
import com.creditcard.services.model.CreditCard;
import com.creditcard.services.util.CCUtil;

public class CreditCardHelper {

    private Random random = new Random(System.currentTimeMillis());
    public static final int LENGTH_OF_CC_NUMBER = 16;
    
    public List<CreditCard> orchestrateCreditCardGeneration(String cardType, int quantity) 
    		throws ExecutionException, CardTypeNotValid{
    	Set<String> creditCards = getCreditCardNumbers(cardType, quantity);
    	List<String> creditCardList = new ArrayList<>(creditCards);
    	return validateAndGetCreditCard(creditCardList, cardType);
    }
    
	public List<CreditCard> validateAndGetCreditCard(List<String> creditCardList, 
				String carType ) throws ExecutionException{
		CreditCardProcessor processor = CreditCardProcessor.getInstance();
		return processor.processCreditCard(creditCardList, carType);
	}
	
	public Set<String> getCreditCardNumbers(String type, int quantity) throws CardTypeNotValid{ 
		  Set<String> set = new LinkedHashSet<>();
		  String startDigit = CCUtil.getStartDigits(type);
		  for(int i=0; i<quantity; i++){
				set.add(generateRandomCreditCardNumber(startDigit, LENGTH_OF_CC_NUMBER));
		  }
		  return set;
	}
	  
	  public String generateRandomCreditCardNumber(String bin, int length) {

		    // The number of random digits that we need to generate is equal to the
		    // total length of the card number minus the start digits given by the
		    // user, minus the check digit at the end.
		    int randomNumberLength = length - (bin.length() + 1);
		    
		    StringBuilder buffer = new StringBuilder(bin);
		    for (int i = 0; i < randomNumberLength; i++) {  
		      int digit = this.random.nextInt(10);
		      buffer.append(digit);
		    }
		    
		    // Do the Luhn algorithm to generate the check digit.
		    int checkDigit = this.getCheckDigit(buffer.toString());
		    buffer.append(checkDigit);
		    
		    return buffer.toString();
		  }
	  
	  
	  private int getCheckDigit(String number) {

		    // Get the sum of all the digits, however we need to replace the value
		    // of every other digit with the same digit multiplied by 2. If this
		    // multiplication yields a number greater than 9, then add the two
		    // digits together to get a single digit number.
		    //
		    // The digits we need to replace will be those in an even position for
		    // card numbers whose length is an even number, or those is an odd
		    // position for card numbers whose length is an odd number. This is
		    // because the Luhn algorithm reverses the card number, and doubles
		    // every other number starting from the second number from the last
		    // position.
		    int sum = 0;
		    int remainder = (number.length() + 1) % 2;
		    for (int i = 0; i < number.length(); i++) {
		  
		      // Get the digit at the current position.
		      int digit = Integer.parseInt(number.substring(i, (i + 1)));
		  
		      if ((i % 2) == remainder) {
		        digit = digit * 2;
		        if (digit > 9) {
		          digit = (digit / 10) + (digit % 10);
		        }
		      }
		      sum += digit;
		    }

		    // The check digit is the number required to make the sum a multiple of
		    // 10.
		    int mod = sum % 10;
		     

		    return ((mod == 0) ? 0 : 10 - mod);
		  }
	  
}

