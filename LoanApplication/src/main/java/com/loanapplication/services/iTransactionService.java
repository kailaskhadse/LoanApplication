package com.loanapplication.services;

import java.util.List;

import com.loanapplication.models.Transaction;



public interface iTransactionService {

	public Transaction addTransaction(Transaction trans);

	public List<Transaction> getTransactionsByCustId(int custId);
}
