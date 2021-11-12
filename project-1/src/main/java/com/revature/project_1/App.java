package com.revature.project_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.EmployeeController;
import com.revature.controller.ManagerController;
import com.revature.controller.RequestController;

import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
			config.addStaticFiles("/public");
		}).start(7000);

	}
}
