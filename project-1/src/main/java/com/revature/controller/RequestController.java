package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import com.revature.model.Request;
import com.revature.service.RequestService;
import com.revature.service.RequestServiceJavaImp;
import io.javalin.http.Handler;

public class RequestController {
	private static RequestService rserv = RequestServiceJavaImp.getRserv();
	private static Gson gson = new Gson();

	public static Handler createRequest = (ctx) -> {
		String body = ctx.body();

		try {
			Request request = gson.fromJson(body, Request.class);
			if (request != null) {
				Request returned = rserv.createRequest(request);
				ctx.result(gson.toJson(returned));
				ctx.status(200);
			} else
				ctx.status(404);

		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		}

	};

	public static Handler getRequestById = (ctx) -> {
		String rid = ctx.pathParam("rid");

		try {
			Request request = rserv.getRequestById(Integer.parseInt(rid));
			String json = gson.toJson(request);
			ctx.result(json);
			ctx.status(200);
		} catch (NumberFormatException e) {
			ctx.status(404);
			e.printStackTrace();
		}
	};

	public static Handler getAllRequests = (ctx) -> {
//		Can have query to search 
		String eid = ctx.queryParam("eid");
		String status = ctx.queryParam("status");
		String sortAmount = ctx.queryParam("sort_amount");

		List<Request> requests = new ArrayList<Request>();
		if (eid != null) {
			requests = rserv.getRequestsByEmployee(Integer.parseInt(eid));

		} else if (status != null) {
			if (status.compareToIgnoreCase("APPROVED") == 0)
				requests = rserv.getRequestsApproved();
			if (status.compareToIgnoreCase("PENDING") == 0)
				requests = rserv.getRequestsPending();
			if (status.compareToIgnoreCase("DENIED") == 0)
				requests = rserv.getRequestsDenied();
		} else if (sortAmount != null) {
			if (sortAmount.compareToIgnoreCase("ASC") == 0)
				requests = rserv.getRequestsAmountAscending();

			if (sortAmount.compareToIgnoreCase("DESC") == 0)
				requests = rserv.getRequestsAmountDescending();
		} else
			requests = rserv.getAllRequests();

		String json = gson.toJson(requests);
		ctx.result(json);
		ctx.status(200);
	};

	public static Handler updateRequest = (ctx) -> {
		String body = ctx.body();
		Request request = gson.fromJson(body, Request.class);
		Request result = rserv.updateRequest(request);

		ctx.result(gson.toJson(result));
		ctx.status(202);

	};

	public static Handler deleteRequest = (ctx) -> {
		String body = ctx.body();
		Request request = gson.fromJson(body, Request.class);
		try {
			boolean result = rserv.deleteRequest(request);

			if (result)
				ctx.status(200);
			else
				ctx.status(404);
		} catch (NumberFormatException e) {
			ctx.status(404);
			e.printStackTrace();
		}
	};
}
