package com.loanapplication.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.TransactionalException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.loanapplication.dao.CustomerRepository;
import com.loanapplication.dao.LoanRepository;
import com.loanapplication.dao.TransactionRepository;
import com.loanapplication.exceptions.CustomerNotFoundException;
import com.loanapplication.exceptions.LoanNotFoundException;
import com.loanapplication.exceptions.TransactionFailedException;
import com.loanapplication.exceptions.TransactionsNotFoundException;
import com.loanapplication.models.Customer;
import com.loanapplication.models.Loan;
import com.loanapplication.models.Transaction;
import com.loanapplication.services.iTransactionService;


@Service
@Primary
public class TransactionServiceImpl implements iTransactionService {

	@Autowired
	private CustomerRepository customerDao;

	@Autowired
	private LoanRepository loanDao;

	@Autowired
	private TransactionRepository transactionDao;

	
	@Override
	public Transaction addTransaction(Transaction transaction) {
		int loanId = transaction.getLoan().getLoanId();
		Loan loan = loanDao.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
		loan.addTransaction(transaction);
		try {
			return transactionDao.save(transaction);
		} catch (Exception e) {
			throw new TransactionFailedException("Transaction Failed for LoanId: " + loanId);
		}
	}

	@Override
	public List<Transaction> getTransactionsByCustId(int customerId) {
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
		try {
			List<Transaction> transactions = transactionDao.findTransactionsByCustomerId(customerId);
			return transactions;
		} catch (Exception e) {
			throw new TransactionsNotFoundException("Transactions not Found for Customer Id: " + customerId);
		}
	}

}
