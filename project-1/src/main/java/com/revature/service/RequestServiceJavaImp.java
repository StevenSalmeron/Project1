package com.revature.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import com.revature.dao.RequestDAO;
import com.revature.dao.RequestDAOJavaImp;
import com.revature.model.Request;

public class RequestServiceJavaImp implements RequestService {
	private static RequestService rserv = null;

	@Inject
	private RequestDAO rdao = RequestDAOJavaImp.getRdao();

	private RequestServiceJavaImp() {
		super();
	}

	public static RequestService getRserv() {
		if (rserv == null)
			rserv = new RequestServiceJavaImp();

		return rserv;
	};

	@Override
	public Request createRequest(Request request) {
		return rdao.createRequest(request);
	}

	@Override
	public Request getRequestById(int rid) {
		return rdao.getRequestById(rid);
	}

	@Override
	public List<Request> getAllRequests() {
		return rdao.getAllRequests();
	}

	@Override
	public List<Request> getRequestsByEmployee(int eid) {
		List<Request> requests = rdao.getAllRequests();
		List<Request> filteredRequests = new ArrayList<Request>();
		for (Request r : requests) {
			if (r.getEid() == eid) {
				filteredRequests.add(r);
			}
		}
		return filteredRequests;
	}

	@Override
	public List<Request> getRequestsApproved() {
		List<Request> requests = rdao.getAllRequests();
		List<Request> filteredRequests = new ArrayList<Request>();
		for (Request r : requests) {
			if (r.getStatus().compareToIgnoreCase("Approved") == 0) {
				filteredRequests.add(r);
			}
		}
		return filteredRequests;
	}

	@Override
	public List<Request> getRequestsDenied() {
		List<Request> requests = rdao.getAllRequests();
		List<Request> filteredRequests = new ArrayList<Request>();
		for (Request r : requests) {
			if (r.getStatus().compareToIgnoreCase("Denied") == 0) {
				filteredRequests.add(r);
			}
		}
		return filteredRequests;
	}

	@Override
	public List<Request> getRequestsPending() {
		List<Request> requests = rdao.getAllRequests();
		List<Request> filteredRequests = new ArrayList<Request>();
		for (Request r : requests) {
			if (r.getStatus().compareToIgnoreCase("Pending") == 0) {
				filteredRequests.add(r);
			}
		}
		return filteredRequests;
	}

	@Override
	public List<Request> getRequestsAmountAscending() {
		List<Request> requests = rserv.getAllRequests();
		Collections.sort(requests, Comparator.comparingDouble(Request::getAmount));
		return requests;
	}

	@Override
	public List<Request> getRequestsAmountDescending() {
		List<Request> requests = rserv.getAllRequests();
		Collections.sort(requests, Comparator.comparingDouble(Request::getAmount).reversed());
		return requests;
	}

	@Override
	public Request updateRequest(Request request) {
		return rdao.updateRequest(request);
	}

	@Override
	public boolean deleteRequest(Request request) {
		return rdao.deleteRequest(request);
	}

}
