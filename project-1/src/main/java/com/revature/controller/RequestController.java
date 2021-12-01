package com.revature.controller;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.Request;
import com.revature.service.AccountService;
import com.revature.service.AccountServiceJavaImp;
import com.revature.service.RequestService;
import com.revature.service.RequestServiceJavaImp;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class RequestController {
	private static AccountService aserv = AccountServiceJavaImp.getAserv();
	private static RequestService rserv = RequestServiceJavaImp.getRserv();
	private static Javalin javalin;

	public static void init(Javalin app) {
		javalin = app;

		app.post("/request", RequestController::insertRequest);
		app.get("/request/:aid", RequestController::getRequest);
		app.post("/request/", RequestController::updateRequest);
	}

	public static void insertRequest(Context ctx) {
		String email = ctx.formParam("email");
		Integer amount = Integer.parseInt(ctx.formParam("amount"));
		String reason = ctx.formParam("reason");
		
		Account account = aserv.getAccountByEmail(email);
		Request newRequest = new Request(1, account.getAid(), amount, reason, "PENDING");
		
		rserv.createRequest(newRequest);
		
		ctx.redirect("employeePage.html");
	}
	
	public static void getRequest(Context ctx) {
		int aid = Integer.parseInt(ctx.pathParam("aid"));
		Account account = aserv.getAccountById(aid);
		if (account.isManager()) {
			List<Request> result = rserv.getAllRequests();
			ctx.json(result);
		}
		else {
			List<Request> result = rserv.getRequestsByAccount(aid);
			ctx.json(result);
		}
	}

	public static void updateRequest(Context ctx) {
		Integer rid = Integer.parseInt(ctx.formParam("rid"));
		String decision = ctx.formParam("decision");
		
		Request updateRequest = rserv.getRequestById(rid);
		updateRequest.setStatus(decision);
		
		rserv.updateRequest(updateRequest);
		
		
	}

}
