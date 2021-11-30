package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.util.ConnectionUtil;

public class AccountDAOJavaImp implements AccountDAO {
	private static AccountDAO adao;

	private AccountDAOJavaImp() {
		super();
	}

	public static AccountDAO getAdao() {

		if (adao == null)
			adao = new AccountDAOJavaImp();

		return adao;
	}

	@Override
	public Account createAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO revaturep1.Accounts VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, account.getAid());
			ps.setString(2, account.getEmail());
			ps.setString(3, account.getPassword());
			ps.setString(4, account.getFirstName());
			ps.setString(5, account.getLastName());
			ps.setBoolean(6, account.isManager());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt(1);
			account.setAid(key);

			return account;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account getAccountById(int aid) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM revaturep1.Accounts WHERE aid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);

			ResultSet rs = ps.executeQuery();
			rs.next();

			Account account = new Account();
			account.setAid(aid);
			account.setEmail(rs.getString("email"));
			account.setPassword(rs.getString("password"));
			account.setFirstName(rs.getString("firstname"));
			account.setLastName(rs.getString("lastname"));
			account.setManager(rs.getBoolean("manager"));

			return account;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Account> getAllAccounts() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM revaturep1.Accounts";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Account> accounts = new ArrayList<Account>();
			Account account;
			while (rs.next()) {
				account = new Account();
				account.setAid(rs.getInt("aid"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				account.setFirstName(rs.getString("firstname"));
				account.setLastName(rs.getString("lastname"));
				account.setManager(rs.getBoolean("manager"));
				accounts.add(account);
			}

			return accounts;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account updateAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Accounts SET email=?,password=?,firstname=?,lastname=?,manager=? WHERE aid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, account.getEmail());
			ps.setString(2, account.getPassword());
			ps.setString(3, account.getFirstName());
			ps.setString(4, account.getLastName());
			ps.setBoolean(5, account.isManager());
			ps.setInt(6, account.getAid());

			if (ps.executeUpdate() > 0) {
				return account;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM revaturep1.Accounts WHERE aid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getAid());

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