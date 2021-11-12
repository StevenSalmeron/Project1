package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Manager;
import com.revature.util.ConnectionUtil;

public class ManagerDAOJavaImp implements ManagerDAO {
	private static ManagerDAO mdao;

	private ManagerDAOJavaImp() {
		super();
	}

	public static ManagerDAO getMdao() {

		if (mdao == null)
			mdao = new ManagerDAOJavaImp();

		return mdao;
	}

	@Override
	public Manager createManager(Manager manager) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO revaturep1.Managers VALUES (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, manager.getMid());
			ps.setString(2, manager.getEmail());
			ps.setString(3, manager.getPassword());
			ps.setString(4, manager.getFirstName());
			ps.setString(5, manager.getLastName());
			ps.execute();

			return manager;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Manager getManagerById(int mid) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM revaturep1.Managers WHERE mid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mid);

			ResultSet rs = ps.executeQuery();
			rs.next();

			Manager manager = new Manager();
			manager.setMid(mid);
			manager.setEmail(rs.getString("email"));
			manager.setPassword(rs.getString("password"));
			manager.setFirstName(rs.getString("firstname"));
			manager.setLastName(rs.getString("lastname"));

			return manager;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Manager> getAllManagers() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM revaturep1.Managers";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Manager> managers = new ArrayList<Manager>();
			Manager manager;
			while (rs.next()) {
				manager = new Manager();
				manager.setMid(rs.getInt("mid"));
				manager.setEmail(rs.getString("email"));
				manager.setPassword(rs.getString("password"));
				manager.setFirstName(rs.getString("firstname"));
				manager.setLastName(rs.getString("lastname"));

				managers.add(manager);
			}

			return managers;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Manager updateManager(Manager manager) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Managers SET email=?,password=?,firstname=?,lastname=? WHERE mid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, manager.getEmail());
			ps.setString(2, manager.getPassword());
			ps.setString(3, manager.getFirstName());
			ps.setString(4, manager.getLastName());
			ps.setInt(5, manager.getMid());

			if (ps.executeUpdate() > 0) {
				return manager;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteManager(Manager manager) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM revaturep1.Managers WHERE mid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, manager.getMid());

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