/**
 * 
 */
package com.assignment.batch.components;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.assignment.batch.entity.CustomerTransaction;


/**
 * @author magantilavakumar
 *
 */
@Component
public class CustomerTrxFieldMapper implements FieldSetMapper<CustomerTransaction> {

	@Override
	public CustomerTransaction mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		
		CustomerTransaction custTrx = new CustomerTransaction();
		custTrx.setAccountNo(fieldSet.readLong("accountNo"));
		custTrx.setTransactionAmount(fieldSet.readDouble("transactionAmount"));
		custTrx.setTransactionDesc(fieldSet.readString("transactionDesc"));
		custTrx.setTransactionDate(fieldSet.readString("transactionDate"));
		custTrx.setTransactionTime(fieldSet.readString("transactionTime"));
		custTrx.setCustomerID(fieldSet.readString("customerID"));
	    return custTrx;
		
	}

}
