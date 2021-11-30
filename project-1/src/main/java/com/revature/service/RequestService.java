package com.revature.service;

import java.util.List;

import com.revature.model.Request;

public interface RequestService {
//  CRUD
	Request createRequest(Request request);

//  READ
	Request getRequestById(int rid);

	List<Request> getAllRequests();

	List<Request> getRequestsByAccount(int aid);

	List<Request> getRequestsApproved();

	List<Request> getRequestsDenied();
	
	List<Request> getRequestsPending();

	List<Request> getRequestsAmountAscending();

	List<Request> getRequestsAmountDescending();


//    Update
	Request updateRequest(Request Request);

//    Delete
	boolean deleteRequest(Request Request);
}
