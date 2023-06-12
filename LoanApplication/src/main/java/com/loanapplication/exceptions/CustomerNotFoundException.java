package com.loanapplication.exceptions;

public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(String message){
		super(message);
	}
}
