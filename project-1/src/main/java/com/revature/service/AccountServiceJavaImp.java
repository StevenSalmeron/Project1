package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOJavaImp;
import com.revature.model.Account;


public class AccountServiceJavaImp implements AccountService {
	private static AccountService aserv;

	@Inject
	private AccountDAO adao = AccountDAOJavaImp.getAdao();

	private AccountServiceJavaImp() {
		super();
	}

	public static AccountService getAserv() {
		if (aserv == null)
			aserv = new AccountServiceJavaImp();

		return aserv;
	};

	@Override
	public Account createAccount(Account account) {
		return adao.createAccount(account);
	}

	@Override
	public Account getAccountById(int aid) {
		return adao.getAccountById(aid);
	}

	@Override
	public Account getAccountByEmail(String email) {
		List<Account> accounts = adao.getAllAccounts();
		for (Account a : accounts) {
			if (a.getEmail().equals(email)) {
				return a;
			}
		}

		return null;
	}
	
	@Override
	public List<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}
	
	@Override
	public List<Account> getAllEmployees() {
		List<Account> employees = adao.getAllAccounts();
		List<Account> filteredRequests = new ArrayList<Account>();
		for (Account e : employees) {
			if (e.isManager() == false) {
				filteredRequests.add(e);
			}
		}
		return filteredRequests;
	}
	
	@Override
	public List<Account> getAllManagers() {
		List<Account> managers = adao.getAllAccounts();
		List<Account> filteredRequests = new ArrayList<Account>();
		for (Account m : managers) {
			if (m.isManager() == true) {
				filteredRequests.add(m);
			}
		}
		return filteredRequests;
	}


	@Override
	public Account updateAccount(Account account) {
		return adao.updateAccount(account);
	}

	@Override
	public boolean deleteAccount(Account account) {
		return adao.deleteAccount(account);
	}
}
