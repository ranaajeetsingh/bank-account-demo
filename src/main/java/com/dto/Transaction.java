package com.dto;

import java.util.Calendar;
import java.util.Date;

import com.constant.TransactionType;

public class Transaction {

	private double transactionAmount;
	private TransactionType transactionType;
	private Date transactionDatetime = Calendar.getInstance().getTime();

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDatetime() {
		return transactionDatetime;
	}

	public void setTransactionDatetime(Date transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}

}
