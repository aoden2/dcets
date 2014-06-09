/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : MyProcess.java
 * 
 * All rights reserved.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class MyProcess {
	List<Integer> sellQueue = null;
	List<Integer> buyQueue = null;
	Hashtable<Integer, OriginOrder> orders = null;

	public MyProcess() {
		sellQueue = new ArrayList<Integer>();
		buyQueue = new ArrayList<Integer>();
		orders = new Hashtable<Integer, OriginOrder>();
		sellQueue.add(0);
		buyQueue.add(0);
	}

	public String procData(String data) {
		String ret = new String();
		OriginOrder oo = EntityFIXHelper.parseOriginOrder(data);
		if (oo.getStatus() == 3){
			addOrderToDatabase(oo);
			procOrder(0, oo);
			// TODO add return msg.
		}
		else if (oo.getStatus() == -3){
			addOrderToDatabase(oo);
			procOrder(1, oo);
			// TODO add return msg.
		}
//		else if (oo.getStatus() == 4){
//			procOrder(2, oo);
//		}
		return ret;
	}

	public synchronized int procOrder(int action, OriginOrder order) {
		// Buy
		if (0 == action) {
			orders.put(order.getId(), order);
			buyQueue.add(order.getId());
			makeBuyHeap(buyQueue.size() - 1);
			matchOrder();
		}
		// Sell
		else if (1 == action) {
			orders.put(order.getId(), order);
			sellQueue.add(order.getId());
			makeSellHeap(sellQueue.size() - 1);
			matchOrder();
		}
		// Revocation
		else if (2 == action) {
			// TODO
		}
		return 0;
	}

	private void addOrderToDatabase(OriginOrder oo) {
		MyJDBC jdbc = new MyJDBC("BrokerServer");
		Connection conn = jdbc.getConnection();
		String sql = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			sql = "insert into BrokerServer.OriginOrder (fid, tid, quantity, cumQtyl, leavesqty, price, date, status) values (?, ?, ?, ?, ?, ?, ?, ?)";
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, oo.getFid());
			pst.setInt(2, oo.getTid());
			pst.setInt(3, oo.getQuantity());
			pst.setInt(4, oo.getCumQtyl());
			pst.setInt(5, oo.getLeavesqty());
			pst.setInt(6, oo.getPrice());
			pst.setTimestamp(7, new Timestamp(oo.getDate().getTime()));
			pst.setInt(8, oo.getStatus());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				oo.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(conn, pst, null);
		}
	}
	
	private void makeBuyHeap(int p) {
		int size = buyQueue.size();
		if (p >= size)
			return;
		if (p != 1) {
			int f = p / 2;
			OriginOrder fOrder = orders.get(buyQueue.get(f));
			OriginOrder pOrder = orders.get(buyQueue.get(p));
			if (fOrder.getPrice() < pOrder.getPrice()
					|| (fOrder.getPrice() == pOrder.getPrice() && fOrder
							.getDate().after(pOrder.getDate()))) {
				Collections.swap(buyQueue, f, p);
				makeBuyHeap(f);
				return;
			}
		}

		int lc = 2 * p;
		if (lc >= size) // leaf node
			return;
		int rc = lc + 1;
		int c = lc;
		OriginOrder pOrder = orders.get(buyQueue.get(p));
		OriginOrder lcOrder = orders.get(buyQueue.get(lc));
		OriginOrder rcOrder = null;
		OriginOrder cOrder = lcOrder;
		if (rc < size) {
			rcOrder = orders.get(buyQueue.get(rc));
			if (lcOrder.getPrice() < rcOrder.getPrice()
					|| (lcOrder.getPrice() == rcOrder.getPrice() && lcOrder
							.getDate().after(rcOrder.getDate()))) {
				c = rc;
				cOrder = rcOrder;
			}
		}
		if (pOrder.getPrice() < cOrder.getPrice()
				|| (pOrder.getPrice() == cOrder.getPrice() && pOrder.getDate()
						.after(cOrder.getDate()))) {
			Collections.swap(buyQueue, p, c);
			makeBuyHeap(c);
		}
	}

	private void makeSellHeap(int p) {
		int size = sellQueue.size();
		if (p >= size)
			return;
		if (p != 1) {
			int f = p / 2;
			OriginOrder fOrder = orders.get(sellQueue.get(f));
			OriginOrder pOrder = orders.get(sellQueue.get(p));
			if (fOrder.getPrice() > pOrder.getPrice()
					|| (fOrder.getPrice() == pOrder.getPrice() && fOrder
							.getDate().after(pOrder.getDate()))) {
				Collections.swap(sellQueue, f, p);
				makeSellHeap(f);
				return;
			}
		}

		int lc = 2 * p;
		if (lc >= size) // leaf node
			return;
		int rc = lc + 1;
		int c = lc;
		OriginOrder pOrder = orders.get(sellQueue.get(p));
		OriginOrder lcOrder = orders.get(sellQueue.get(lc));
		OriginOrder rcOrder = null;
		OriginOrder cOrder = lcOrder;
		if (rc < size) {
			rcOrder = orders.get(sellQueue.get(rc));
			if (lcOrder.getPrice() > rcOrder.getPrice()
					|| (lcOrder.getPrice() == rcOrder.getPrice() && lcOrder
							.getDate().after(rcOrder.getDate()))) {
				c = rc;
				cOrder = rcOrder;
			}
		}
		if (pOrder.getPrice() > cOrder.getPrice()
				|| (pOrder.getPrice() == cOrder.getPrice() && pOrder.getDate()
						.after(cOrder.getDate()))) {
			Collections.swap(sellQueue, p, c);
			makeSellHeap(c);
		}
	}

	private void matchOrder() {
		boolean isMatched = false;
		try {
			int topSell = sellQueue.get(1);
			int topBuy = buyQueue.get(1);
			OriginOrder sell = orders.get(topSell);
			OriginOrder buy = orders.get(topBuy);
			if (buy.getPrice() >= sell.getPrice()) {
				int qty = Math.max(sell.getLeavesqty(), buy.getLeavesqty());
				int price = 0;
				if (sell.getDate().before(buy.getDate()))
					price = sell.getPrice();
				else
					price = buy.getPrice();
				// create final order
				FinalOrder fOrder = new FinalOrder();
				fOrder.setOsid(sell.getId());
				fOrder.setObid(buy.getId());
				fOrder.setPrice(price);
				fOrder.setQuantity(qty);
				fOrder.setStatus(0);
				// update sell order
				sell.setCumQtyl(sell.getCumQtyl() + qty);
				sell.setLeavesqty(sell.getQuantity() - sell.getCumQtyl());
				sell.setStatus(-2);
				if (0 == sell.getLeavesqty()) {
					sellQueue.set(1, sellQueue.get(sellQueue.size() - 1));
					sellQueue.remove(sellQueue.size() - 1);
					makeSellHeap(1);
					sell.setStatus(0);
					orders.remove(sell.getId());
				}
				// update buy order
				buy.setCumQtyl(buy.getCumQtyl() + qty);
				buy.setLeavesqty(buy.getQuantity() - buy.getCumQtyl());
				buy.setStatus(2);
				if (0 == buy.getLeavesqty()) {
					buyQueue.set(1, buyQueue.get(buyQueue.size() - 1));
					buyQueue.remove(buyQueue.size() - 1);
					makeBuyHeap(1);
					buy.setStatus(0);
					orders.remove(buy.getId());
				}

				MyJDBC jdbc = new MyJDBC("BrokerServer");
				Connection conn = jdbc.getConnection();
				String sql = null;
				PreparedStatement pst = null;
				try {
					// Add fOrder to Dayabase
					sql = "insert into BrokerServer.FinalOrder (osid, obid, quantity, price, status) values (?, ?, ?, ?, ?)";
					pst = conn.prepareStatement(sql);
					pst.setInt(1, fOrder.getOsid());
					pst.setInt(2, fOrder.getObid());
					pst.setInt(3, fOrder.getQuantity());
					pst.setInt(4, fOrder.getPrice());
					pst.setInt(5, fOrder.getStatus());
					pst.executeUpdate();
					pst.close();
					// Update buy to Dayabase
					sql = "update BrokerServer.OriginOrder set BrokerServer.OriginOrder.cumQtyl = ?, BrokerServer.OriginOrder.leavesqty = ?, BrokerServer.OriginOrder.status = ? where BrokerServer.OriginOrder.id = ?";
					pst = conn.prepareStatement(sql);
					pst.setInt(1, buy.getCumQtyl());
					pst.setInt(2, buy.getLeavesqty());
					pst.setInt(3, buy.getStatus());
					pst.setInt(4, buy.getId());
					pst.executeUpdate();
					// Update sell to Dayabase
					pst.setInt(1, sell.getCumQtyl());
					pst.setInt(2, sell.getLeavesqty());
					pst.setInt(3, sell.getStatus());
					pst.setInt(4, sell.getId());
					pst.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					jdbc.close(conn, pst, null);
				}
				isMatched = true;
			}
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
		if (isMatched)
			matchOrder();
	}
}

