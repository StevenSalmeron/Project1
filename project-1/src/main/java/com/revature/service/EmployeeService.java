package com.revature.service;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeService {
// CREATE
	Employee createEmployee(Employee employee);

//	READ
	Employee getEmployeeById(int eid);
	
	Employee getEmployeeByEmail(String email);

	List<Employee> getAllEmployees();

//	UPDATE
	Employee updateEmployee(Employee employee);

//	DELETE
	boolean deleteEmployee(Employee employee);
}
