package com.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
	private long accountId;
	private String accountName;
	private double accountBalance;
	private double interestRate;
	private String accountNote;
	private List<Transaction> transactions = new ArrayList<>();

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		if (accountName.length() > 16)
			this.accountName = accountName.substring(0, 15);
		else
			this.accountName = accountName;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public String getAccountNote() {
		return accountNote;
	}

	public void setAccountNote(String accountNote) {
		if (accountNote.length() > 80)
			this.accountNote = accountNote.substring(0, 79);
		else
			this.accountNote = accountNote;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountId == other.accountId;
	}

}
