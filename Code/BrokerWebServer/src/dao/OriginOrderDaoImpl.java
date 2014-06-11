package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import entity.OriginOrder;

public class OriginOrderDaoImpl implements OriginOrderDao {

	private JDBCUtil jdbc = new JDBCUtil("BrokerServer");

	@Override
	public List<OriginOrder> listOriginOrder(int tid) {
		List<OriginOrder> ans = new ArrayList<OriginOrder>();
		Connection conn = jdbc.getConnection();
		String sql = "select * from originorder where tid=?;";
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, tid);
			rs = pst.executeQuery();
			while (rs.next()) {
				ans.add(new OriginOrder(rs.getInt("id"), rs.getInt("fid"), rs
						.getInt("tid"), rs.getInt("quantity"), rs
						.getInt("cumQtyl"), rs.getInt("leavesqty"), rs
						.getInt("price"), rs.getDate("date"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, rs);
		}
		return ans;
	}
	
	@Override
	public List<OriginOrder> listOriginOrder() {
		System.out.println("Im here waiting oo;");
		List<OriginOrder> ans = new ArrayList<OriginOrder>();
		Connection conn = jdbc.getConnection();
		String sql = "select * from originorder;";
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				ans.add(new OriginOrder(rs.getInt("id"), rs.getInt("fid"), rs
						.getInt("tid"), rs.getInt("quantity"), rs
						.getInt("cumQtyl"), rs.getInt("leavesqty"), rs
						.getInt("price"), rs.getDate("date"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, rs);
		}
		System.out.println(ans.size());
		return ans;
	}

}
