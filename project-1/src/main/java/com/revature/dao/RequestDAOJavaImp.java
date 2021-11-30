package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Request;
import com.revature.util.ConnectionUtil;

public class RequestDAOJavaImp implements RequestDAO {
	private static RequestDAO rdao;

	private RequestDAOJavaImp() {
		super();
	}

	public static RequestDAO getRdao() {

		if (rdao == null)
			rdao = new RequestDAOJavaImp();

		return rdao;
	}

	@Override
	public Request createRequest(Request request) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Requests VALUES (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, request.getRid());
			ps.setInt(2, request.getAid());
			ps.setDouble(3, request.getAmount());
			ps.setString(4, request.getReason());
			ps.setString(5, request.getStatus());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt(1);
			request.setRid(key);
			
			return request;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Request getRequestById(int rid) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Requests WHERE rid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rid);

			ResultSet rs = ps.executeQuery();
			rs.next();

			Request request = new Request();
			request.setRid(rs.getInt("rid"));
			request.setAid(rs.getInt("aid"));
			request.setAmount(rs.getDouble("amount"));
			request.setReason(rs.getString("reason"));
			request.setStatus(rs.getString("status"));

			return request;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Request> getAllRequests() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Requests";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Request> requests = new ArrayList<Request>();
			Request request;
			while (rs.next()) {
				request = new Request();
				request.setRid(rs.getInt("rid"));
				request.setAid(rs.getInt("aid"));
				request.setAmount(rs.getDouble("amount"));
				request.setReason(rs.getString("reason"));
				request.setStatus(rs.getString("status"));

				requests.add(request);
			}

			return requests;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Request updateRequest(Request request) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Requests SET aid=?,amount=?,reason=?,status=? WHERE rid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, request.getAid());
			ps.setDouble(2, request.getAmount());
			ps.setString(3, request.getReason());
			ps.setString(4, request.getStatus());
			ps.setInt(5, request.getRid());

			if (ps.executeUpdate() > 0) {
				return request;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteRequest(Request request) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM Requests WHERE rid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, request.getRid());

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
