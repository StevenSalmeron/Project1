package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDAO {
	Employee createEmployee(Employee employee);

	Employee getEmployeeById(int eid);

	List<Employee> getAllEmployees();

	Employee updateEmployee(Employee employee);

	boolean deleteEmployee(Employee employee);

}
