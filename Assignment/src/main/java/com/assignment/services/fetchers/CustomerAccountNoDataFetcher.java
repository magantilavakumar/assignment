package com.assignment.services.fetchers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.batch.entity.CustomerTransaction;
import com.assignment.batch.repository.CustomerRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author magantilavakumar
 *
 */
@Component
public class CustomerAccountNoDataFetcher implements DataFetcher<List<CustomerTransaction>> {

	private static Logger log = LoggerFactory.getLogger(CustomerAccountNoDataFetcher.class);
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Override
	public List<CustomerTransaction> get(DataFetchingEnvironment environment) {
		// TODO Auto-generated method stub
		Long accountNo =  environment.getArgument("id");
		log.info("CustomerAccountNoDataFetcher:"+accountNo);
		
		return customerRepo.searchByAccountNo(accountNo);
	}

}
