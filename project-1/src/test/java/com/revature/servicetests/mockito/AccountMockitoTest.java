package com.revature.servicetests.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.dao.AccountDAO;
import com.revature.model.Account;
import com.revature.service.AccountService;
import com.revature.service.AccountServiceJavaImp;

import java.util.ArrayList;
import java.util.List;

class AccountMockitoTest {

	@Mock
	private AccountDAO adaoMock;

	@InjectMocks
	private AccountService aservMock = AccountServiceJavaImp.getAserv();

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Order(1)
	void testGetAccountByEmail() {

		Account account = new Account();
		account.setAid(0);
		account.setEmail("mock@gmail.com");
		account.setPassword("mockpass");
		account.setFirstName("Mock");
		account.setLastName("Mocker");
		account.setManager(false);

		Account account2 = new Account();
		account2.setAid(0);
		account2.setEmail("mock1@gmail.com");
		account2.setPassword("mockpass1");
		account2.setFirstName("Mock1");
		account2.setLastName("Mocker1");
		account2.setManager(false);

		List<Account> accountList = new ArrayList<Account>();
		accountList.add(account);
		accountList.add(account2);

		Mockito.when(adaoMock.getAllAccounts()).thenReturn(accountList);

		Account result = aservMock.getAccountByEmail("mock1@gmail.com");

		Assertions.assertEquals("mock1@gmail.com", result.getEmail());
	}

	@Test
	@Order(2)
	void testGetAllEmployees() {

		Account account = new Account();
		account.setAid(0);
		account.setEmail("mock@gmail.com");
		account.setPassword("mockpass");
		account.setFirstName("Mock");
		account.setLastName("Mocker");
		account.setManager(true);

		Account account2 = new Account();
		account2.setAid(0);
		account2.setEmail("mock1@gmail.com");
		account2.setPassword("mockpass1");
		account2.setFirstName("Mock1");
		account2.setLastName("Mocker1");
		account2.setManager(false);

		List<Account> accountList = new ArrayList<Account>();
		accountList.add(account);
		accountList.add(account2);

		Mockito.when(adaoMock.getAllAccounts()).thenReturn(accountList);

		List<Account> result = aservMock.getAllEmployees();

		Assertions.assertEquals(1, result.size());
	}

	@Test
	@Order(3)
	void testGetAllManagers() {
		Account account = new Account();
		account.setAid(0);
		account.setEmail("mock@gmail.com");
		account.setPassword("mockpass");
		account.setFirstName("Mock");
		account.setLastName("Mocker");
		account.setManager(true);

		Account account2 = new Account();
		account2.setAid(0);
		account2.setEmail("mock1@gmail.com");
		account2.setPassword("mockpass1");
		account2.setFirstName("Mock1");
		account2.setLastName("Mocker1");
		account2.setManager(false);
		
		Account account3 = new Account();
		account3.setAid(0);
		account3.setEmail("mock2@gmail.com");
		account3.setPassword("mockpass2");
		account3.setFirstName("Mock2");
		account3.setLastName("Mocker2");
		account3.setManager(true);

		List<Account> accountList = new ArrayList<Account>();
		accountList.add(account);
		accountList.add(account2);
		accountList.add(account3);

		Mockito.when(adaoMock.getAllAccounts()).thenReturn(accountList);

		List<Account> result = aservMock.getAllManagers();

		Assertions.assertEquals(2, result.size());
	}

}
