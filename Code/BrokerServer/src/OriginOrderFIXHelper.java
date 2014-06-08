/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : OriginOrderFIXHelper.java
 * 
 * All rights reserved.
 */

import java.util.Date;

public class OriginOrderFIXHelper {
	// private String broker_id = "1";

	public static String OriginOrder2Fix(OriginOrder oo) {
		return "";
	}

	public static OriginOrder parseOriginOrder(String fixData) {
		MyFIX mf = new MyFIX(fixData);
		int init_tag = 100;
		OriginOrder oo = new OriginOrder();
		oo.setTid(Integer.parseInt(mf.getTag(49)));
		oo.setFid(Integer.parseInt(mf.getTag((init_tag + 1))));
		oo.setQuantity(Integer.parseInt(mf.getTag((init_tag + 2))));
		oo.setPrice(Integer.parseInt(mf.getTag((init_tag + 3))));
		oo.setStatus(Integer.parseInt(mf.getTag((init_tag + 4))));
		oo.setLeavesqty(oo.getQuantity());
		oo.setCumQtyl(0);
		oo.setDate(new Date(System.currentTimeMillis()));
		return oo;
	}
}
