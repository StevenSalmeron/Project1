package com.revature.daotests;

import com.revature.model.Account;
import org.junit.jupiter.api.*;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOJavaImp;

import java.util.List;

import javax.transaction.Transactional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class AccountDAOTest {

	private static AccountDAO adao = AccountDAOJavaImp.getAdao();

	@Test
	@Order(1)
	void createAccount() {
		Account e = new Account(1, "test@gmail.com", "test123", "Test", "Tester", false);

		Account result = adao.createAccount(e);

		Assertions.assertNotEquals(0, result.getAid());
	}

	@Test
	@Order(2)
	void getAccountById() {
		List<Account> accountList = adao.getAllAccounts();
		Account account = accountList.get(accountList.size() - 1);
		Account result = adao.getAccountById(account.getAid());

		Assertions.assertEquals(account.getEmail(), result.getEmail());
	}

	@Test
	@Order(3)
	void getAllAccounts() {
		List<Account> results = adao.getAllAccounts();

		Assertions.assertNotEquals(0, results.size());
	}

	@Test
	@Order(4)
	void updateAccount() {
		List<Account> accountList = adao.getAllAccounts();
		Account account = accountList.get(accountList.size() - 1);
		account.setEmail("testUpdated@gmail.com");
		Account result = adao.updateAccount(account);

		Assertions.assertEquals("testUpdated@gmail.com", result.getEmail());
	}

	@Test
	@Order(5)
	void deleteAccount() {
		List<Account> accountList = adao.getAllAccounts();
		Account account = accountList.get(accountList.size() - 1);

		boolean result = adao.deleteAccount(account);

		Assertions.assertEquals(true, result);

	}
}