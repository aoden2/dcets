package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import entity.Future;

public class FutureDaoImpl implements FutureDao {

	private JDBCUtil jdbc = new JDBCUtil("bws");
	
	@Override
	public List<Future> listAllFuture() {
		List<Future> ans = new ArrayList<Future>();
		Connection conn = jdbc.getConnection();
		String sql = "select * from future;";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs= st.executeQuery(sql);
			while (rs.next())
			{
				Future f = new Future();
				f.setId(rs.getInt("id"));
				f.setName(rs.getString("name"));
				f.setPeriod(rs.getString("period"));
				f.setCategory(rs.getString("category"));
				ans.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, st, rs);
		}
		
		return ans;
	}

}
