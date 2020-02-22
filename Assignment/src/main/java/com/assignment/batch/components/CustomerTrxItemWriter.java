package com.assignment.batch.components;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.batch.entity.CustomerTransaction;
import com.assignment.batch.repository.CustomerRepo;

@Component
public class CustomerTrxItemWriter implements ItemWriter<CustomerTransaction>{

	private static final Logger log = LoggerFactory.getLogger(CustomerTrxItemWriter.class);
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public void write(List<? extends CustomerTransaction> items) throws Exception {
		// TODO Auto-generated method stub
		log.info("CustomerTrxItemWriter");
		customerRepo.saveAll(items);
	}

}
