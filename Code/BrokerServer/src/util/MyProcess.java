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
import java.util.Hashtable;
import java.util.List;

import entity.OriginOrder;

public class MyProcess {
	List<Integer> sellQuene = null;
	List<Integer> buyQuene = null;
	Hashtable<Integer, OriginOrder> orders = null;
	
	public MyProcess() {
		sellQuene = new ArrayList<Integer>();
		buyQuene = new ArrayList<Integer>();
		orders = new Hashtable<Integer, OriginOrder>();
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
			makeBuyHeap();
			matchOrder();
		}
		// Sell
		else if (1 == action) {
			orders.put(order.getId(), order);
			sellQuene.add(order.getId());
			makeSellHeap();
			matchOrder();
		}
		// Revocation
		else if (2 == action) {
			//TODO
		}
		return 0;
	}
	
	private void makeBuyHeap() {
		// TODO
	}
	
	private void makeSellHeap() {
		// TODO
	}
	
	private void matchOrder() {
		// TODO
	}
}
