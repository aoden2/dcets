package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.JDBCUtil;
import entity.BrokerInfo;

public class BrokerDaoImpl implements BrokerDao {
	private JDBCUtil jdbc = new JDBCUtil("dcetsT1");

	public List<BrokerInfo> getAllBrokers() {
		List<BrokerInfo> brokers = new ArrayList<BrokerInfo>();
		Connection conn = jdbc.getConnection();
		String sql = "select * from brokerinfo;";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				BrokerInfo broker = new BrokerInfo();
				broker.setId(rs.getInt("id"));
				broker.setName(rs.getString("name"));
				broker.setIp(rs.getString("ip"));
				broker.setPort(rs.getInt("port"));
				broker.setPass(rs.getString("pass"));
				brokers.add(broker);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, st, rs);
		}
		return brokers;
	}

	public int addBroker(String name, String ip, int port, String pass) {
		int rtn = 0;
		Connection conn = jdbc.getConnection();
		String sql = "insert into brokerinfo (name, ip, port, pass) values (?, ?, ?, ?);";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, ip);
			pst.setInt(3, port);
			pst.setString(4, pass);
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, null);
		}
		return rtn;
	}

	public BrokerInfo getBrokerbyId(int bid){
		BrokerInfo broker = new BrokerInfo();
		Connection conn = jdbc.getConnection();
		String sql = "select * from brokerinfo where id=?;";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, bid);
			rs= pst.executeQuery();
			rs.last();
			broker.setId(rs.getInt("id"));
			broker.setName(rs.getString("name"));
			broker.setIp(rs.getString("ip"));
			broker.setPort(rs.getInt("port"));
			broker.setPass(rs.getString("pass"));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				jdbc.close(conn, pst, rs);
			}
		return broker;
	}
}
