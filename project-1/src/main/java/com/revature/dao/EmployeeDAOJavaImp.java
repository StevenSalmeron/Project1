package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOJavaImp implements EmployeeDAO {
	private static EmployeeDAO edao;

	private EmployeeDAOJavaImp() {
		super();
	}

	public static EmployeeDAO getEdao() {

		if (edao == null)
			edao = new EmployeeDAOJavaImp();

		return edao;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO revaturep1.Employees VALUES (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getEid());
			ps.setString(2, employee.getEmail());
			ps.setString(3, employee.getPassword());
			ps.setString(4, employee.getFirstName());
			ps.setString(5, employee.getLastName());
			ps.execute();

			return employee;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployeeById(int eid) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM revaturep1.Employees WHERE eid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, eid);

			ResultSet rs = ps.executeQuery();
			rs.next();

			Employee employee = new Employee();
			employee.setEid(eid);
			employee.setEmail(rs.getString("email"));
			employee.setPassword(rs.getString("password"));
			employee.setFirstName(rs.getString("firstname"));
			employee.setLastName(rs.getString("lastname"));

			return employee;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM revaturep1.Employees";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Employee> employees = new ArrayList<Employee>();
			Employee employee;
			while (rs.next()) {
				employee = new Employee();
				employee.setEid(rs.getInt("eid"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));

				employees.add(employee);
			}

			return employees;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Employees SET email=?,password=?,firstname=?,lastname=? WHERE eid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, employee.getEmail());
			ps.setString(2, employee.getPassword());
			ps.setString(3, employee.getFirstName());
			ps.setString(4, employee.getLastName());
			ps.setInt(5, employee.getEid());

			if (ps.executeUpdate() > 0) {
				return employee;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM revaturep1.Employees WHERE eid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getEid());

			if (ps.executeUpdate() > 0) {
				return true;
			}

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}