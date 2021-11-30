package com.revature.dao;

import java.util.List;

import com.revature.model.Account;

public interface AccountDAO {
	Account createAccount(Account account);

	Account getAccountById(int aid);

	List<Account> getAllAccounts();

	Account updateAccount(Account account);

	boolean deleteAccount(Account account);

}
