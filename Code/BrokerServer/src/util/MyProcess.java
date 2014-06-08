/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : MyProcess.java
 * 
 * All rights reserved.
 */
package util;

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
		// TODO
		// processs the data to origin data;
		// if is a Buy, Sell or Revocation, go to the procOrder function.
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
				return ;
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
				|| (pOrder.getPrice() == cOrder.getPrice() && pOrder
						.getDate().after(cOrder.getDate()))) {
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
				return ;
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
				|| (pOrder.getPrice() == cOrder.getPrice() && pOrder
						.getDate().after(cOrder.getDate()))) {
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
				if (0 == sell.getLeavesqty()) {
					sellQuene.set(1, sellQuene.get(sellQuene.size() - 1));
					sellQuene.remove(sellQuene.size() - 1);
					makeSellHeap(1);
					orders.remove(sell.getId());
				}
				// update buy order
				buy.setCumQtyl(buy.getCumQtyl() + qty);
				buy.setLeavesqty(buy.getQuantity() - buy.getCumQtyl());
				if (0 == buy.getLeavesqty()) {
					buyQuene.set(1, buyQuene.get(buyQuene.size() - 1));
					buyQuene.remove(buyQuene.size() - 1);
					makeBuyHeap(1);
					orders.remove(buy.getId());
				}

				// TODO
				// Add fOrder to Dayabase
				// Update buy to Dayabase
				// Update sell to Dayabase
				isMatched = true;
			}
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
		if (isMatched)
			matchOrder();
	}
}
