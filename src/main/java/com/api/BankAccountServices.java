package com.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.constant.TransactionType;
import com.contract.AccountRequest;
import com.contract.TransactionRequest;
import com.dto.Account;
import com.dto.Transaction;

@RestController
@RequestMapping("/service")
public class BankAccountServices {
	private List<Account> list = new ArrayList<Account>();

	@PostMapping("create")
	public ResponseEntity<Account> createAccount(
			@RequestBody AccountRequest request) {

		Account account = new Account();

		if (list.isEmpty()) {
			account.setAccountId(10000000);
		} else {
			account.setAccountId(list.get(list.size() - 1).getAccountId() + 1);
		}

		account.setAccountName(request.getAccountName());
		account.setAccountBalance(request.getInitialDeposit());
		account.setAccountNote(request.getAccountNote());
		account.setInterestRate(request.getInterestRate());
		Transaction transaction = new Transaction();
		transaction.setTransactionType(TransactionType.CREDIT);
		transaction.setTransactionAmount(request.getInitialDeposit());

		account.getTransactions().add(transaction);
		list.add(account);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@GetMapping("list")
	public List<Account> listAccounts() {
		return list;
	}

	@PutMapping("/deposit")
	public ResponseEntity<String> deposit(
			@RequestBody TransactionRequest request) {

		Account account = new Account();
		account.setAccountId(request.getAccountId());
		int index = list.indexOf(account);
		if (index > -1) {
			account = list.get(index);
			account.setAccountBalance(account.getAccountBalance()
					+ request.getTrasactionAmount());

			Transaction transaction = new Transaction();
			transaction.setTransactionType(TransactionType.CREDIT);
			transaction.setTransactionAmount(request.getTrasactionAmount());

			account.getTransactions().add(transaction);

			return new ResponseEntity<String>(
					"Account balance $" + account.getAccountBalance(),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Account does not exist",
					HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/withdraw")
	public ResponseEntity<String> withdraw(
			@RequestBody TransactionRequest request) {

		Account account = new Account();
		account.setAccountId(request.getAccountId());
		int index = list.indexOf(account);
		if (index > -1) {
			account = list.get(index);
			account.setAccountBalance(account.getAccountBalance()
					- request.getTrasactionAmount());

			Transaction transaction = new Transaction();
			transaction.setTransactionType(TransactionType.DEBIT);
			transaction.setTransactionAmount(request.getTrasactionAmount());

			account.getTransactions().add(transaction);

			return new ResponseEntity<String>(
					"Account balance $" + account.getAccountBalance(),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Account does not exist",
					HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/annualizedInterest")
	public ResponseEntity<String> addAnnualInterest(
			@RequestParam long accountId) {

		Account request = new Account();
		request.setAccountId(accountId);
		int index = list.indexOf(request);
		if (index > -1) {
			request = list.get(index);

			if (request.getAccountBalance() > 0) {
				double amount = (request.getAccountBalance()
						* request.getInterestRate()) / 100;
				request.setAccountBalance(request.getAccountBalance() + amount);
			}

			return new ResponseEntity<String>(
					"Account balance $" + request.getAccountBalance(),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Account does not exist",
					HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/editInterestRate")
	public ResponseEntity<String> editInterestRate(@RequestParam long accountId,
			@RequestParam double interestRate) {

		Account request = new Account();
		request.setAccountId(accountId);
		int index = list.indexOf(request);
		if (index > -1) {
			request = list.get(index);
			request.setInterestRate(interestRate);
			return new ResponseEntity<String>(
					"Account balance $" + request.getAccountBalance(),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Account does not exist",
					HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/editAccountNote")
	public ResponseEntity<String> editNote(@RequestParam long accountId,
			@RequestParam String note) {

		Account request = new Account();
		request.setAccountId(accountId);
		int index = list.indexOf(request);
		if (index > -1) {
			request = list.get(index);
			request.setAccountNote(note);
			return new ResponseEntity<String>(
					"Account balance $" + request.getAccountBalance(),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Account does not exist",
					HttpStatus.BAD_REQUEST);
		}

	}
}
