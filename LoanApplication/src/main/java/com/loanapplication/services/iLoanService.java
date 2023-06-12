package com.loanapplication.services;

import java.util.List;

import com.loanapplication.models.Loan;



public interface iLoanService {

	public Loan applyLoan(Loan l);

	public List<Loan> getLoansByCustomerId(int custId);

	public void foreCloseLoan(int loanId);

}
