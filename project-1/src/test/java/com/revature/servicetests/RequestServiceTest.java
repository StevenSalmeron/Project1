package com.revature.servicetests;

import com.revature.model.Request;
import org.junit.jupiter.api.*;

import com.revature.service.RequestService;
import com.revature.service.RequestServiceJavaImp;

import java.util.List;

import javax.transaction.Transactional;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class RequestServiceTest {

	private static RequestService aserv = RequestServiceJavaImp.getRserv();

	@Test
	@Order(1)
	void createRequest() {
		Request a = new Request(0, 1, 22.15, "Test1", "Approved");

		Request result = aserv.createRequest(a);

		Assertions.assertNotNull(result);
	}

	@Test
	@Order(2)
	void getRequestById() {
		List<Request> requestList = aserv.getAllRequests();
		Request request = requestList.get(requestList.size() - 1);
		Request result = aserv.getRequestById(request.getRid());
		Assertions.assertEquals(request.getReason(), result.getReason());
	}

	@Test
	@Order(3)
	void getAllRequestsByAccount() {
		List<Request> requests = aserv.getRequestsByAccount(1);

		Assertions.assertEquals(1, requests.size());
	}

	@Test
	@Order(4)
	void getRequestsApproved() {
		List<Request> results = aserv.getRequestsApproved();
		Assertions.assertEquals(1, results.size());
	}

	@Test
	@Order(5)
	void getRequestsDenied() {
		List<Request> results = aserv.getRequestsDenied();
		Assertions.assertEquals(0, results.size());
	}

	@Test
	@Order(6)
	void getRequestsPending() {
		List<Request> results = aserv.getRequestsPending();
		Assertions.assertEquals(0, results.size());
	}

	@Test
	@Order(7)
	void updateRequest() {
		List<Request> requests = aserv.getAllRequests();
		Request update = requests.get(requests.size() - 1);
		update.setReason("Updated Reason");
		Request result = aserv.updateRequest(update);

		Assertions.assertEquals("Updated Reason", result.getReason());
	}

	@Test
	@Order(8)
	void deleteRequest() {

		List<Request> requests = aserv.getAllRequests();
		Request deleteMe = requests.get(requests.size() - 1);
		boolean result = aserv.deleteRequest(deleteMe);

		Assertions.assertEquals(true, result);
	}

}
