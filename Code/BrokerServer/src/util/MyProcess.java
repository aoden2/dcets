/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : MyProcess.java
 * 
 * All rights reserved.
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import entity.FinalOrder;
import entity.OriginOrder;

public class MyProcess {
	List<Integer> sellQuene = null;
	List<Integer> buyQuene = null;
	Hashtable<Integer, OriginOrder> orders = null;

	public MyProcess() {
		sellQuene = new ArrayList<Integer>();
		buyQuene = new ArrayList<Integer>();
		orders = new Hashtable<Integer, OriginOrder>();
		sellQuene.add(0);
		buyQuene.add(0);
	}

	public String procData(String data) {
		String ret = null;
		OriginOrder oo = OriginOrderFIXHelper.parseOriginOrder(data);
		if (oo.getStatus() == 3){
			procOrder(0, oo);
		}
		else if (oo.getStatus() == -3){
			procOrder(1, oo);
		}
		else if (oo.getStatus() == 4){
			procOrder(2, oo);
		}
		// TODO
		// order should be added to database here.
		return ret;
	}

	public synchronized int procOrder(int action, OriginOrder order) {
		// Buy
		if (0 == action) {
			orders.put(order.getId(), order);
			buyQuene.add(order.getId());
			makeBuyHeap(buyQuene.size() - 1);
			matchOrder();
		}
		// Sell
		else if (1 == action) {
			orders.put(order.getId(), order);
			sellQuene.add(order.getId());
			makeSellHeap(sellQuene.size() - 1);
			matchOrder();
		}
		// Revocation
		else if (2 == action) {
			// TODO
		}
		return 0;
	}

	private void makeBuyHeap(int p) {
		int size = buyQuene.size();
		if (p >= size)
			return;
		if (p != 1) {
			int f = p / 2;
			OriginOrder fOrder = orders.get(buyQuene.get(f));
			OriginOrder pOrder = orders.get(buyQuene.get(p));
			if (fOrder.getPrice() < pOrder.getPrice()
					|| (fOrder.getPrice() == pOrder.getPrice() && fOrder
							.getDate().after(pOrder.getDate()))) {
				Collections.swap(buyQuene, f, p);
				makeBuyHeap(f);
				return;
			}
		}

		int lc = 2 * p;
		if (lc >= size) // leaf node
			return;
		int rc = lc + 1;
		int c = lc;
		OriginOrder pOrder = orders.get(buyQuene.get(p));
		OriginOrder lcOrder = orders.get(buyQuene.get(lc));
		OriginOrder rcOrder = null;
		OriginOrder cOrder = lcOrder;
		if (rc < size) {
			rcOrder = orders.get(buyQuene.get(rc));
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
			Collections.swap(buyQuene, p, c);
			makeBuyHeap(c);
		}
	}

	private void makeSellHeap(int p) {
		int size = sellQuene.size();
		if (p >= size)
			return;
		if (p != 1) {
			int f = p / 2;
			OriginOrder fOrder = orders.get(sellQuene.get(f));
			OriginOrder pOrder = orders.get(sellQuene.get(p));
			if (fOrder.getPrice() > pOrder.getPrice()
					|| (fOrder.getPrice() == pOrder.getPrice() && fOrder
							.getDate().after(pOrder.getDate()))) {
				Collections.swap(sellQuene, f, p);
				makeSellHeap(f);
				return;
			}
		}

		int lc = 2 * p;
		if (lc >= size) // leaf node
			return;
		int rc = lc + 1;
		int c = lc;
		OriginOrder pOrder = orders.get(sellQuene.get(p));
		OriginOrder lcOrder = orders.get(sellQuene.get(lc));
		OriginOrder rcOrder = null;
		OriginOrder cOrder = lcOrder;
		if (rc < size) {
			rcOrder = orders.get(sellQuene.get(rc));
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
			Collections.swap(sellQuene, p, c);
			makeSellHeap(c);
		}
	}

	private void matchOrder() {
		boolean isMatched = false;
		try {
			int topSell = sellQuene.get(0);
			int topBuy = buyQuene.get(0);
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
					sellQuene.set(1, sellQuene.get(sellQuene.size() - 1));
					sellQuene.remove(sellQuene.size() - 1);
					makeSellHeap(1);
					sell.setStatus(0);
					orders.remove(sell.getId());
				}
				// update buy order
				buy.setCumQtyl(buy.getCumQtyl() + qty);
				buy.setLeavesqty(buy.getQuantity() - buy.getCumQtyl());
				buy.setStatus(2);
				if (0 == buy.getLeavesqty()) {
					buyQuene.set(1, buyQuene.get(buyQuene.size() - 1));
					buyQuene.remove(buyQuene.size() - 1);
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

