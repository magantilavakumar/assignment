package com.assignment.batch.components;
/**
 * @author magantilavakumar
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.assignment.batch.entity.CustomerTransaction;
@Component
public class CustomerTrxItemProcessor implements ItemProcessor<CustomerTransaction, CustomerTransaction> {

	private static final Logger log = LoggerFactory.getLogger(CustomerTrxItemProcessor.class);
	@Override
	public CustomerTransaction process(CustomerTransaction item) throws Exception {
		// TODO Auto-generated method stub
		log.info(item.toString());
		if(item.isAnyFieldEmpty())
			return item;
		else 
			return null;
	}
	

}
