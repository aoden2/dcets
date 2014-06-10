package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import util.WSParser;
import client.httpRequest;
import entity.Future;

public class FutureDaoImpl implements FutureDao {
	private JDBCUtil jdbc = new JDBCUtil("dcetsT1");

	@SuppressWarnings("resource")
	public void refreshFutures() {
		String future_str = httpRequest.sendGet(
				"http://59.78.3.25:8080/BrokerWebServer/services/getFuture",
				null);
		List<Future> l = WSParser.parseAllFuture(future_str);
		Connection conn = jdbc.getConnection();
		PreparedStatement pst = null;
		String sql = "";
		try {
			sql = "drop table if exists future;";
			pst = conn.prepareStatement(sql);
			pst.execute();
			sql = "create table future(id int primary key auto_increment,category varchar(255),name varchar(255),period varchar(80));";
			pst = conn.prepareStatement(sql);
			pst.execute();
			for (int i = 0; i < l.size(); i++) {
				sql = "insert into future(category, name, period) values(?,?,?);";
				pst = conn.prepareStatement(sql);
				pst.setString(1, l.get(i).getCategory());
				pst.setString(2, l.get(i).getName());
				pst.setString(3, l.get(i).getPeriod());
				pst.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, null);
		}
	}

	public List<Future> getAllFutures(int bid) {
		List<Future> futures = new ArrayList<Future>();
		// BrokerDao bd = new BrokerDaoImpl();
		// BrokerInfo broker = bd.getBrokerbyId(bid);
		Connection conn = jdbc.getConnection();
		ResultSet rs = null;
		String sql = "select * from future;";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Future f = new Future(rs.getInt("id"),
						rs.getString("category"), rs.getString("name"),
						rs.getString("period"));
				futures.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, null);
		}
		// String future_str = "";// HttpHelper.SendHttpRequest("get",
		// broker.getIp()+":8080/BrokerWebServer/services/getFuture",
		// null);

		return futures;
	}

	public int getFutureByNamePeriod(String name, String period) {
		int ans = 0;
		Connection conn = jdbc.getConnection();
		ResultSet rs = null;
		String sql = "select * from future where name=? && period=?;";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, period);
			rs = pst.executeQuery();
			if (rs.next())
				ans = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, null);
		}
		return ans;
	}

	public List<Integer> getFutureByName(String name) {
		List<Integer> ans = new ArrayList<>();
		Connection conn = jdbc.getConnection();
		ResultSet rs = null;
		String sql = "select * from future where name=?;";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				ans.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, rs);
		}
		return ans;
	}

	public List<String> getAllFutureName() {
		List<String> ans = new ArrayList<>();
		Connection conn = jdbc.getConnection();
		ResultSet rs = null;
		String sql = "select * from future;";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				ans.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, rs);
		}
		return ans;
	}

	public Future getFutureById(int id) {
		Future ans = null;
		Connection conn = jdbc.getConnection();
		ResultSet rs = null;
		String sql = "select * from future where id = ?;";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				ans = new Future(rs.getInt("id"), rs.getString("category"),
						rs.getString("name"), rs.getString("period"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, rs);
		}
		return ans;
	}

}
