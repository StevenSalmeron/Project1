package com.revature.dao;

import java.util.List;

import com.revature.model.Request;

public interface RequestDAO {

	Request createRequest(Request request);

	Request getRequestById(int rid);

	List<Request> getAllRequests();

	Request updateRequest(Request request);

	boolean deleteRequest(Request request);
}
