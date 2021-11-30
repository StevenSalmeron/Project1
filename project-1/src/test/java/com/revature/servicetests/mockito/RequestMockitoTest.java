package com.revature.servicetests.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.dao.RequestDAO;
import com.revature.model.Request;
import com.revature.service.RequestService;
import com.revature.service.RequestServiceJavaImp;

import java.util.ArrayList;
import java.util.List;

class RequestMockitoTest {

	@Mock
	private RequestDAO rdaoMock;

	@InjectMocks
	private RequestService rservMock = RequestServiceJavaImp.getRserv();

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Order(1)
	void testGetRequestsByAccount() {

		Request request = new Request();
		request.setRid(0);
		request.setAid(8);
		request.setAmount(25.50);
		request.setReason("Gas");
		request.setStatus("Approved");

		Request request1 = new Request();
		request1.setRid(0);
		request1.setAid(8);
		request1.setAmount(45.53);
		request1.setReason("Expensive Gas");
		request1.setStatus("Denied");

		Request request2 = new Request();
		request2.setRid(0);
		request2.setAid(9);
		request2.setAmount(16.54);
		request2.setReason("Questionable Gas");
		request2.setStatus("Pending");

		Request request3 = new Request();
		request3.setRid(0);
		request3.setAid(10);
		request3.setAmount(200.90);
		request3.setReason("Gas Tank Replacement");
		request3.setStatus("Pending");

		List<Request> requestList = new ArrayList<Request>();
		requestList.add(request);
		requestList.add(request1);
		requestList.add(request2);
		requestList.add(request3);

		Mockito.when(rdaoMock.getAllRequests()).thenReturn(requestList);

		List<Request> result = rservMock.getRequestsByAccount(8);

		Assertions.assertEquals(2, result.size());
	}

	@Test
	@Order(2)
	void testGetRequestsApproved() {
		Request request = new Request();
		request.setRid(0);
		request.setAid(8);
		request.setAmount(25.50);
		request.setReason("Gas");
		request.setStatus("Approved");

		Request request1 = new Request();
		request1.setRid(0);
		request1.setAid(8);
		request1.setAmount(45.53);
		request1.setReason("Expensive Gas");
		request1.setStatus("Denied");

		Request request2 = new Request();
		request2.setRid(0);
		request2.setAid(9);
		request2.setAmount(16.54);
		request2.setReason("Questionable Gas");
		request2.setStatus("Pending");

		Request request3 = new Request();
		request3.setRid(0);
		request3.setAid(10);
		request3.setAmount(200.90);
		request3.setReason("Gas Tank Replacement");
		request3.setStatus("Pending");

		List<Request> requestList = new ArrayList<Request>();
		requestList.add(request);
		requestList.add(request1);
		requestList.add(request2);
		requestList.add(request3);

		Mockito.when(rdaoMock.getAllRequests()).thenReturn(requestList);

		List<Request> result = rservMock.getRequestsApproved();

		Assertions.assertEquals(1, result.size());

	}

	@Test
	@Order(3)
	void testGetRequestsDenied() {
		Request request = new Request();
		request.setRid(0);
		request.setAid(8);
		request.setAmount(25.50);
		request.setReason("Gas");
		request.setStatus("Approved");

		Request request1 = new Request();
		request1.setRid(0);
		request1.setAid(8);
		request1.setAmount(45.53);
		request1.setReason("Expensive Gas");
		request1.setStatus("Denied");

		Request request2 = new Request();
		request2.setRid(0);
		request2.setAid(9);
		request2.setAmount(16.54);
		request2.setReason("Questionable Gas");
		request2.setStatus("Pending");

		Request request3 = new Request();
		request3.setRid(0);
		request3.setAid(10);
		request3.setAmount(200.90);
		request3.setReason("Gas Tank Replacement");
		request3.setStatus("Pending");

		List<Request> requestList = new ArrayList<Request>();
		requestList.add(request);
		requestList.add(request1);
		requestList.add(request2);
		requestList.add(request3);

		Mockito.when(rdaoMock.getAllRequests()).thenReturn(requestList);

		List<Request> result = rservMock.getRequestsDenied();

		Assertions.assertEquals(1, result.size());
	}

	@Test
	@Order(4)
	void testGetRequestsPending() {
		Request request = new Request();
		request.setRid(0);
		request.setAid(8);
		request.setAmount(25.50);
		request.setReason("Gas");
		request.setStatus("Approved");

		Request request1 = new Request();
		request1.setRid(0);
		request1.setAid(8);
		request1.setAmount(45.53);
		request1.setReason("Expensive Gas");
		request1.setStatus("Denied");

		Request request2 = new Request();
		request2.setRid(0);
		request2.setAid(9);
		request2.setAmount(16.54);
		request2.setReason("Questionable Gas");
		request2.setStatus("Pending");

		Request request3 = new Request();
		request3.setRid(0);
		request3.setAid(10);
		request3.setAmount(200.90);
		request3.setReason("Gas Tank Replacement");
		request3.setStatus("Pending");

		List<Request> requestList = new ArrayList<Request>();
		requestList.add(request);
		requestList.add(request1);
		requestList.add(request2);
		requestList.add(request3);

		Mockito.when(rdaoMock.getAllRequests()).thenReturn(requestList);

		List<Request> result = rservMock.getRequestsPending();

		Assertions.assertEquals(2, result.size());
	}

	@Test
	@Order(5)
	void testGetRequestsAmountAscending() {
		Request request = new Request();
		request.setRid(0);
		request.setAid(8);
		request.setAmount(25.50);
		request.setReason("Gas");
		request.setStatus("Approved");

		Request request1 = new Request();
		request1.setRid(0);
		request1.setAid(8);
		request1.setAmount(45.53);
		request1.setReason("Expensive Gas");
		request1.setStatus("Denied");

		Request request2 = new Request();
		request2.setRid(0);
		request2.setAid(9);
		request2.setAmount(16.54);
		request2.setReason("Questionable Gas");
		request2.setStatus("Pending");

		Request request3 = new Request();
		request3.setRid(0);
		request3.setAid(10);
		request3.setAmount(200.90);
		request3.setReason("Gas Tank Replacement");
		request3.setStatus("Pending");

		List<Request> requestList = new ArrayList<Request>();
		requestList.add(request);
		requestList.add(request1);
		requestList.add(request2);
		requestList.add(request3);

		Mockito.when(rdaoMock.getAllRequests()).thenReturn(requestList);

		List<Request> result = rservMock.getRequestsAmountAscending();

		Assertions.assertEquals(25.50, result.get(1).getAmount());
		Assertions.assertEquals(45.53, result.get(2).getAmount());
	}

	@Test
	@Order(6)
	void testGetRequestsAmountDescending() {
		Request request = new Request();
		request.setRid(0);
		request.setAid(8);
		request.setAmount(25.50);
		request.setReason("Gas");
		request.setStatus("Approved");

		Request request1 = new Request();
		request1.setRid(0);
		request1.setAid(8);
		request1.setAmount(45.53);
		request1.setReason("Expensive Gas");
		request1.setStatus("Denied");

		Request request2 = new Request();
		request2.setRid(0);
		request2.setAid(9);
		request2.setAmount(16.54);
		request2.setReason("Questionable Gas");
		request2.setStatus("Pending");

		Request request3 = new Request();
		request3.setRid(0);
		request3.setAid(10);
		request3.setAmount(200.90);
		request3.setReason("Gas Tank Replacement");
		request3.setStatus("Pending");

		List<Request> requestList = new ArrayList<Request>();
		requestList.add(request);
		requestList.add(request1);
		requestList.add(request2);
		requestList.add(request3);

		Mockito.when(rdaoMock.getAllRequests()).thenReturn(requestList);

		List<Request> result = rservMock.getRequestsAmountDescending();

		Assertions.assertEquals(25.50, result.get(2).getAmount());
		Assertions.assertEquals(45.53, result.get(1).getAmount());
	}
}
