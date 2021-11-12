package com.revature.service;

import java.util.List;

import javax.inject.Inject;

import com.revature.dao.EmployeeDAO;
import com.revature.model.Employee;

public class EmployeeServiceJavaImp implements EmployeeService {
	private static EmployeeService eserv = null;

	@Inject
	private EmployeeDAO edao;

	private EmployeeServiceJavaImp() {
		super();
	}

	public static EmployeeService getEserv() {
		if (eserv == null)
			eserv = new EmployeeServiceJavaImp();

		return eserv;
	};

	@Override
	public Employee createEmployee(Employee employee) {
		return edao.createEmployee(employee);
	}

	@Override
	public Employee getEmployeeById(int eid) {
		return edao.getEmployeeById(eid);
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		List<Employee> employees = edao.getAllEmployees();
		for (Employee e : employees) {
			if (e.getEmail().compareToIgnoreCase(email) == 0) {
				return e;
			}
		}

		return null;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return edao.getAllEmployees();
	}


	@Override
	public Employee updateEmployee(Employee employee) {
		return edao.updateEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		return edao.deleteEmployee(employee);
	}
}
