package com.revature.daotests;

import com.revature.model.Employee;
import org.junit.jupiter.api.*;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOJavaImp;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeDAOTest
{

	private static EmployeeDAO edao = EmployeeDAOJavaImp.getEdao();
    @Test
    @Order(1)
    void createEmployee()
    {
        Employee e = new Employee(1,
                "test@gmail.com",
                "test123",
                "Test",
                "Tester");

        Employee result = edao.createEmployee(e);

        Assertions.assertNotEquals(0, result.getEid());
    }

    @Test
    @Order(2)
    void getEmployeeById()
    {
        List<Employee> employeeList = edao.getAllEmployees();
        Employee employee = employeeList.get(employeeList.size()-1);
        Employee result = edao.getEmployeeById(employee.getEid());

        Assertions.assertEquals(employee.getEmail(), result.getEmail());
    }

    @Test
    @Order(3)
    void getAllEmployees()
    {
        List<Employee> result = edao.getAllEmployees();

        Assertions.assertNotEquals(0, result.size());
    }

    @Test
    @Order(4)
    void updateEmployee()
    {
        List<Employee> employeeList = edao.getAllEmployees();
        Employee employee = employeeList.get(employeeList.size()-1);
        employee.setEmail("testUpdated@gmail.com");
        Employee result = edao.updateEmployee(employee);

        Assertions.assertEquals("testUpdated@gmail.com",result.getEmail());
    }

    @Test
    @Order(5)
    void deleteEmployee()
    {
        List<Employee> employeeList = edao.getAllEmployees();
        Employee employee = employeeList.get(employeeList.size()-1);

        boolean result = edao.deleteEmployee(employee);

        Assertions.assertEquals(true, result);

    }
}