package com.loanapplication.exceptions;

public class TransactionFailedException extends RuntimeException {

	public TransactionFailedException(String message) {
		super(message);
	}
	
}
