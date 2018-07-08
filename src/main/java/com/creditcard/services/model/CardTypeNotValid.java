package com.creditcard.services.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Card type provided is not found in the system.")
public class CardTypeNotValid extends Exception {
	private static final long serialVersionUID = 100L;
}
