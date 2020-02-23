package com.assignment.batch.repository;

import java.util.List;

/**
 * @author magantilavakumar
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.assignment.batch.entity.CustomerTransaction;
import com.assignment.modal.SearchCustomerTransaction;


public interface CustomerRepo extends JpaRepository<CustomerTransaction, Long>{
	public List<CustomerTransaction> searchByCustomerId(String customerID);
	public List<CustomerTransaction> searchByAccountNo(Long accountNo);
	public List<CustomerTransaction> searchByTrxDesc(String transactionDesc);
}
