package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import util.JDBCUtil;

public class UserDao implements UserDaoImpl {
	
	private JDBCUtil jdbc = new JDBCUtil("meet");

	public List<User> listAllUser() {
		List<User> users = new ArrayList<User>();
		Connection conn = jdbc.getConnection();
		String sql = "select * from user;";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs= st.executeQuery(sql);
			while (rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, st, rs);
		}
		
		return users;
	}
	
	public User listUser(String username){
		Connection conn = jdbc.getConnection();
		String sql = "select * from user where username = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		User u = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			while (rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public void addUser(User user) {
		Connection conn = jdbc.getConnection();
		String sql = "insert into user (username, password) values (?, ?);";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, null);
		}

	}

}
