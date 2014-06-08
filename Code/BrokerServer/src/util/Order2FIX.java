/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : Order2FIX.java
 * 
 * All rights reserved.
 */
package util;

import java.util.ArrayList;
import java.util.List;

import entity.OriginOrder;

public class Order2FIX {
	public static int getFutureId(String fixData) {
		MyFIX mf = new MyFIX(fixData);
		int init_tag = 101;
		return Integer.parseInt(mf.getTag((init_tag + 1)));
	}
	
	public static OriginOrder FIX2Order(String fixData) {
		OriginOrder order = new OriginOrder();
		MyFIX mf = new MyFIX(fixData);
		
//		int init_tag = 101;
//		while (mf.getTag(init_tag) != null) {
//			OriginOrder oo = new OriginOrder();
//			oo.setFid(Integer.parseInt(mf.getTag((init_tag + 1))));
//			oo.setQuantity(Integer.parseInt(mf.getTag((init_tag + 2))));
//			oo.setPrice(Integer.parseInt(mf.getTag((init_tag + 3))));
//			oo.setStatus(Integer.parseInt(mf.getTag((init_tag + 4))));
//			oo.setBid(Integer.parseInt(mf.getTag((init_tag + 5))));
//			init_tag = init_tag + 10;
//			oos.add(oo);
//		}
		return order;
	}
}
