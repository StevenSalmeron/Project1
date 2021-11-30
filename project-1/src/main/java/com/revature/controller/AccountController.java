package com.revature.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.revature.model.Account;
import com.revature.service.AccountService;
import com.revature.service.AccountServiceJavaImp;

public class AccountController {

	private static AccountService aserv = AccountServiceJavaImp.getAserv();

	private static Javalin javalin;

	public static void addToApp(Javalin app) {
		javalin = app;
		app.post("/login", AccountController::login);
	}

	public static void login(Context ctx) {

		String email = ctx.formParam("email");
		String password = ctx.formParam("password");
		Account account = aserv.getAccountByEmail(email);

		if (account == null) {
			ctx.redirect("/login.html");
		} else if (!account.getPassword().equals(password)) {
			ctx.redirect("/login.html");
		} else {
			if (account.isManager()) {
				ctx.redirect("/managerPage.html");
			}

			else {
				ctx.redirect("/employeePage.html");
			}
		}
	}

}