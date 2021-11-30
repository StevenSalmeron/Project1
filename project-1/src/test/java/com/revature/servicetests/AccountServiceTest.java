package com.revature.servicetests;

import com.revature.model.Account;
import org.junit.jupiter.api.*;

import com.revature.service.AccountService;
import com.revature.service.AccountServiceJavaImp;

import java.util.List;

import javax.transaction.Transactional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class AccountServiceTest {

	private static AccountService aserv = AccountServiceJavaImp.getAserv();

	@Test
	@Order(1)
	void createAccount() {
		Account a = new Account(0, "testTM@gmail.com", "test123", "Test", "Tester", true);

		Account result = aserv.createAccount(a);

		Assertions.assertNotEquals(0, result.getAid());
	}

	@Test
	@Order(2)
	void getAccountById() {
		List<Account> accountList = aserv.getAllAccounts();
		Account account = accountList.get(accountList.size() - 1);
		Account result = aserv.getAccountById(account.getAid());
		Assertions.assertEquals(account.getEmail(), result.getEmail());
	}

	@Test
	@Order(3)
	void getAccountByEmail() {
		Account result = aserv.getAccountByEmail("testTM@gmail.com");

		Assertions.assertEquals("testTM@gmail.com", result.getEmail());
	}

	@Test
	@Order(4)
	void getAllAccounts() {
		List<Account> accounts = aserv.getAllAccounts();

		Assertions.assertNotEquals(0, accounts.size());
	}

	@Test
	@Order(5)
	void getAllEmployees() {
		List<Account> results = aserv.getAllEmployees();
		Assertions.assertEquals(0, results.size());
	}
	
	@Test
	@Order(6)
	void getAllManagers() {
		List<Account> results = aserv.getAllManagers();
		Assertions.assertNotEquals(0, results.size());
	}

	
	@Test
	@Order(7)
	void updateAccount() {
		List<Account> accounts = aserv.getAllAccounts();
		Account update = accounts.get(accounts.size() - 1);
		update.setEmail("TestingTM@gmail.com");
		Account result = aserv.updateAccount(update);

		Assertions.assertEquals("TestingTM@gmail.com", result.getEmail());
	}

	@Test
	@Order(8)
	void deleteAccount() {

		List<Account> accounts = aserv.getAllAccounts();
		Account deleteMe = accounts.get(accounts.size() - 1);
		boolean result = aserv.deleteAccount(deleteMe);

		Assertions.assertEquals(true, result);
	}

}
