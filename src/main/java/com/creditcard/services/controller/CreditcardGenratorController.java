package com.creditcard.services.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.services.helper.CreditCardHelper;
import com.creditcard.services.model.CardTypeNotValid;
import com.creditcard.services.model.CreditCard;

@RestController
public class CreditcardGenratorController {

	@RequestMapping("/{cardType}/{quantity}")
	public ResponseEntity<List<CreditCard>> generateCreditCards(@PathVariable String cardType,
			@PathVariable int quantity) throws CardTypeNotValid{
		List<CreditCard> creditcards;
		CreditCardHelper creditCardHelper = new CreditCardHelper();
		try {
			creditcards = creditCardHelper.orchestrateCreditCardGeneration(cardType, quantity);
		} catch (ExecutionException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(creditcards,HttpStatus.OK);
	}
	
	
}