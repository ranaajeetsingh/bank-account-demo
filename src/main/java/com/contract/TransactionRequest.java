package com.contract;

public class TransactionRequest {
	private long accountId;
	private double trasactionAmount;

	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public double getTrasactionAmount() {
		return trasactionAmount;
	}
	public void setTrasactionAmount(double trasactionAmount) {
		this.trasactionAmount = trasactionAmount;
	}

}
