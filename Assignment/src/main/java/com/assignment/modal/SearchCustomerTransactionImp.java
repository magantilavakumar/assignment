package com.assignment.modal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.assignment.batch.entity.CustomerTransaction;

public class SearchCustomerTransactionImp implements SearchCustomerTransaction {
//
//	@Autowired
//	EntityManager entityManger;
//	
//	@Override
//	public List<CustomerTransaction> searchByCustomerId(String customerID) {
//		// TODO Auto-generated method stub
//		
//		Query query = entityManger.createNamedQuery("searchBy.customerId");
//		query.setParameter(1, customerID);
//		List<CustomerTransaction> lstByCustId = query.getResultList();
//		return lstByCustId;
//	}
//
//	@Override
//	public List<CustomerTransaction> searchByAccountNo(String accountNo) {
//		// TODO Auto-generated method stub
//		Query query =  entityManger.createNamedQuery("searchBy.accountNo",CustomerTransaction.class);
//		query.setParameter(1, accountNo);
//		List<CustomerTransaction> lstByAccountNo = query.getResultList();
//		return lstByAccountNo;
//	}
//
//	@Override
//	public List<CustomerTransaction> searchByTrxDesc(String transactionDesc) {
//		// TODO Auto-generated method stub
//		Query query =   entityManger.createNamedQuery("searchBy.transactionDesc",CustomerTransaction.class);
//		query.setParameter(1, transactionDesc);
//		List<CustomerTransaction> lstByTrxDesc = query.getResultList();
//		return lstByTrxDesc;
//	}
//
}
