package com.revature.service;

import java.util.List;

import com.revature.model.Account;

public interface AccountService {
// CREATE
	Account createAccount(Account account);

//	READ
	Account getAccountById(int aid);
	
	Account getAccountByEmail(String email);

	List<Account> getAllAccounts();
	
	List<Account> getAllEmployees();
	
	List<Account> getAllManagers();

//	UPDATE
	Account updateAccount(Account account);

//	DELETE
	boolean deleteAccount(Account account);
}
