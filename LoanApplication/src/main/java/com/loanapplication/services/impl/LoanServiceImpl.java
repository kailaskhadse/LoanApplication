package com.loanapplication.services.impl;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.loanapplication.dao.CustomerRepository;
import com.loanapplication.dao.LoanRepository;
import com.loanapplication.exceptions.CustomerNotFoundException;
import com.loanapplication.exceptions.LoanNotFoundException;
import com.loanapplication.models.Customer;
import com.loanapplication.models.Loan;
import com.loanapplication.services.iLoanService;

@Service
@Primary
public class LoanServiceImpl implements iLoanService {

	@Autowired
	private LoanRepository loanDao;

	@Autowired
	private CustomerRepository customerDao;

	private org.slf4j.Logger logger = LoggerFactory.getLogger(Loan.class);

	public Loan applyLoan(Loan loan) {
		int customerId = loan.getCustomer().getId();
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Cusotmer Not Found: " + customerId));
		customer.addLoan(loan);
		return loanDao.save(loan);
	}

	@Override
	public List<Loan> getLoansByCustomerId(int customerId) {
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
		return customer.getLoans();
	}

	@Override
	public void foreCloseLoan(int loanId) {
		Loan loan = loanDao.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
		loanDao.delete(loan);
	}

}
