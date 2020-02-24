package com.assignment.services.fetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.assignment.batch.entity.CustomerTransaction;
import com.assignment.batch.repository.CustomerRepo;
import com.assignment.services.fetchers.util.CustomerTrxOffSetLimitRequest;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllCustomerTransactionFetcher implements DataFetcher<Page<CustomerTransaction>> {

	
	@Autowired
	CustomerRepo customerRepo;
	
	@Override
	public Page<CustomerTransaction> get(DataFetchingEnvironment environment) {
		// TODO Auto-generated method stub
		Integer skip = environment.getArgument("skip");
		Integer first = environment.getArgument("first");
		return customerRepo.findAll(new CustomerTrxOffSetLimitRequest(skip.intValue(),first.intValue()));
	}

}
