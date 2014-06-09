package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import entity.FinalOrder;

public class FinalOrderDaoImpl implements FinalOrderDao {

	private JDBCUtil jdbc = new JDBCUtil("bws");

	@Override
	public List<FinalOrder> listAllFinalOrder() {
		List<FinalOrder> ans = new ArrayList<FinalOrder>();
		Connection conn = jdbc.getConnection();
		String sql = "select * from finalorder;";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ans.add(new FinalOrder(rs.getInt("id"), rs.getInt("osid"), rs
						.getInt("obid"), rs.getInt("quantity"), rs
						.getInt("price"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, st, rs);
		}

		return ans;
	}

}
