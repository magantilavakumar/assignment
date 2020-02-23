package com.assignment.batch.entity;

/**
 * @author magantilavakumar
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity

@NamedQueries({
	@NamedQuery(name = "CustomerTransaction.searchByCustomerId",query = "SELECT C FROM CustomerTransaction C WHERE customerID =?1"),
	@NamedQuery(name = "CustomerTransaction.searchByAccountNo",query = "SELECT C FROM CustomerTransaction C WHERE accountNo =?1"),
	@NamedQuery(name = "CustomerTransaction.searchByTrxDesc",query = "SELECT C FROM CustomerTransaction C WHERE transactionDesc =?1")
})
public class CustomerTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long accountNo;
	private double transactionAmount;
	private String transactionDesc;
	private String transactionDate;
	private String transactionTime;
	private String customerID;
	
	public CustomerTransaction() {
		
	}
	
	public CustomerTransaction(Long accountNo, double transactionAmount, String transactionDesc, String transactionDate,
			String transactionTime, String customerID) {
		super();
		this.accountNo = accountNo;
		this.transactionAmount = transactionAmount;
		this.transactionDesc = transactionDesc;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.customerID = customerID;
	}
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionDesc() {
		return transactionDesc;
	}
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public String toString() {
		StringBuffer sB = new StringBuffer("Customer Info->");
		sB.append("AccountNo:"+accountNo);
		sB.append(",TransactionAmount:"+transactionAmount);
		sB.append(",Description:"+transactionDesc);
		sB.append(",Trx Date:"+transactionDate);
		sB.append(",Trx Time:"+transactionTime);
		sB.append(",CustomerID:"+customerID);		
		return sB.toString();

	}
	
	public boolean isAnyFieldEmpty() {
		if(accountNo==0)
			return false;
		else if(transactionAmount==0 || transactionAmount<0)
			return false;
		else if(transactionDate==null || "".equals(transactionDate))
			return false;
		else if(transactionTime==null || "".equals(transactionTime))
			return false;
		else if(customerID==null || "".equals(customerID))
			return false;
		else
			return true;
	}

}
