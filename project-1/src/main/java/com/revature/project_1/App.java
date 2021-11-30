package com.revature.project_1;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import com.revature.controller.AccountController;

public class App {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/public", Location.CLASSPATH);
		}).start(7090);

		AccountController.addToApp(app);

		app.get("/", ctx -> ctx.html("<h1>Welcome to Javalin </h1>"));

	}
}
