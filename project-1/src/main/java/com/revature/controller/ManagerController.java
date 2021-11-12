package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import com.revature.model.Manager;
import com.revature.service.ManagerService;
import com.revature.service.ManagerServiceJavaImp;
import io.javalin.http.Handler;

public class ManagerController {
	private static ManagerService mserv = ManagerServiceJavaImp.getMserv();
	private static Gson gson = new Gson();

	public static Handler createManager = (ctx) -> {
		String body = ctx.body();

		try {
			Manager Manager = gson.fromJson(body, Manager.class);
			if (Manager != null) {
				Manager returned = mserv.createManager(Manager);
				ctx.result(gson.toJson(returned));
				ctx.status(200);
			} else
				ctx.status(404);

		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		}

	};

	public static Handler getManagerById = (ctx) -> {
		String eid = ctx.pathParam("eid");

		try {
			Manager Manager = mserv.getManagerById(Integer.parseInt(eid));
			String json = gson.toJson(Manager);
			ctx.result(json);
			ctx.status(200);
		} catch (NumberFormatException e) {
			ctx.status(404);
			e.printStackTrace();
		}
	};
	
	public static Handler getManagerByEmail = (ctx) -> {
		String email = ctx.pathParam("email");

		try {
			Manager Manager = mserv.getManagerByEmail(email);
			String json = gson.toJson(Manager);
			ctx.result(json);
			ctx.status(200);
		} catch (NumberFormatException e) {
			ctx.status(404);
			e.printStackTrace();
		}
	};

	public static Handler getAllManagers = (ctx) -> {
		String email = ctx.queryParam("email");

		List<Manager> Managers = new ArrayList<Manager>();

		if (email != null) {
			Managers.add(mserv.getManagerByEmail(email));
		} else {
			Managers = mserv.getAllManagers();
		}

		String json = gson.toJson(Managers);
		ctx.result(json);
		ctx.status(200);
	};

	public static Handler updateManager = (ctx) -> {
		String body = ctx.body();
		Manager Manager = gson.fromJson(body, Manager.class);
		Manager result = mserv.updateManager(Manager);

		ctx.result(gson.toJson(result));
		ctx.status(202);

	};

	public static Handler deleteManager = (ctx) -> {
		String body = ctx.body();
		Manager Manager = gson.fromJson(body, Manager.class);
		try {
			boolean result = mserv.deleteManager(Manager);

			if (result == true)
				ctx.status(200);
			else
				ctx.status(404);
		} catch (NumberFormatException e) {
			ctx.status(404);
			e.printStackTrace();
		}
	};
}
