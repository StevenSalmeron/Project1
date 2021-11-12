package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import com.revature.model.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceJavaImp;
import io.javalin.http.Handler;

public class EmployeeController {
	private static EmployeeService eserv = EmployeeServiceJavaImp.getEserv();
	private static Gson gson = new Gson();

	public static Handler createEmployee = (ctx) -> {
		String body = ctx.body();

		try {
			Employee Employee = gson.fromJson(body, Employee.class);
			if (Employee != null) {
				Employee returned = eserv.createEmployee(Employee);
				ctx.result(gson.toJson(returned));
				ctx.status(200);
			} else
				ctx.status(404);

		} catch (Exception e) {
			ctx.status(404);
			e.printStackTrace();
		}

	};

	public static Handler getEmployeeById = (ctx) -> {
		String eid = ctx.pathParam("eid");

		try {
			Employee Employee = eserv.getEmployeeById(Integer.parseInt(eid));
			String json = gson.toJson(Employee);
			ctx.result(json);
			ctx.status(200);
		} catch (NumberFormatException e) {
			ctx.status(404);
			e.printStackTrace();
		}
	};
	
	public static Handler getEmployeeByEmail = (ctx) -> {
		String email = ctx.pathParam("email");

		try {
			Employee Employee = eserv.getEmployeeByEmail(email);
			String json = gson.toJson(Employee);
			ctx.result(json);
			ctx.status(200);
		} catch (NumberFormatException e) {
			ctx.status(404);
			e.printStackTrace();
		}
	};

	public static Handler getAllEmployees = (ctx) -> {
		String email = ctx.queryParam("email");

		List<Employee> Employees = new ArrayList<Employee>();

		if (email != null) {
			Employees.add(eserv.getEmployeeByEmail(email));
		} else {
			Employees = eserv.getAllEmployees();
		}

		String json = gson.toJson(Employees);
		ctx.result(json);
		ctx.status(200);
	};

	public static Handler updateEmployee = (ctx) -> {
		String body = ctx.body();
		Employee Employee = gson.fromJson(body, Employee.class);
		Employee result = eserv.updateEmployee(Employee);

		ctx.result(gson.toJson(result));
		ctx.status(202);

	};

	public static Handler deleteEmployee = (ctx) -> {
		String body = ctx.body();
		Employee Employee = gson.fromJson(body, Employee.class);
		try {
			boolean result = eserv.deleteEmployee(Employee);

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
