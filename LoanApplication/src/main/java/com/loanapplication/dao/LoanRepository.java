package com.loanapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.loanapplication.models.Customer;
import com.loanapplication.models.Loan;



@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer> {

	@Query("select l from Loan l where l.id=?1")
	Customer findByCustomerId(int custId);
}
