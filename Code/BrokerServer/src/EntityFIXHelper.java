/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : OriginOrderFIXHelper.java
 * 
 * All rights reserved.
 */

import java.util.Date;

public class EntityFIXHelper {
//	private static String broker_id = "1";
//
//	public static String traderOrder2Fix(OriginOrder topBuy,
//			OriginOrder topSell, int lastPrice, String date) {
//		MyFIX mf = new MyFIX("tradeOrderResponse", null, broker_id, 1);
//		if (null == topBuy && null == topSell) {
//			mf.setTag(101, "-8");// type
//		} else if (null == topBuy) {
//			mf.setTag(101, "-3");
//			mf.setTag(102, String.valueOf(topSell.getFid()));// fid
//			mf.setTag(103, String.valueOf(topSell.getLeavesqty()));// sell qty
//			mf.setTag(104, String.valueOf(topSell.getPrice()));// sell price
//			mf.setTag(107, String.valueOf(lastPrice));
//			mf.setTag(108, date);
//		} else if (null == topSell) {
//			mf.setTag(101, "3");
//			mf.setTag(102, String.valueOf(topBuy.getFid()));// fid
//			mf.setTag(105, String.valueOf(topBuy.getLeavesqty()));// buy qty
//			mf.setTag(106, String.valueOf(topBuy.getPrice()));// buy price
//			mf.setTag(107, String.valueOf(lastPrice));
//			mf.setTag(108, date);
//		} else {
//			mf.setTag(101, "0");
//			mf.setTag(102, String.valueOf(topBuy.getFid()));// fid
//			mf.setTag(103, String.valueOf(topSell.getLeavesqty()));// sell qty
//			mf.setTag(104, String.valueOf(topSell.getPrice()));// sell price
//			mf.setTag(105, String.valueOf(topBuy.getLeavesqty()));// buy qty
//			mf.setTag(106, String.valueOf(topBuy.getPrice()));// buy price
//			mf.setTag(107, String.valueOf(lastPrice));
//			mf.setTag(108, date);
//		}
//
//		return mf.getFIX();
//	}
//
//	public static OriginOrder parseOriginOrder(String fixData) {
//		MyFIX mf = new MyFIX(fixData);
//		int init_tag = 100;
//		OriginOrder oo = new OriginOrder();
//		oo.setTid(Integer.parseInt(mf.getTag(49)));
//		oo.setFid(Integer.parseInt(mf.getTag((init_tag + 1))));
//		oo.setQuantity(Integer.parseInt(mf.getTag((init_tag + 2))));
//		oo.setPrice(Integer.parseInt(mf.getTag((init_tag + 3))));
//		oo.setStatus(Integer.parseInt(mf.getTag((init_tag + 4))));
//		oo.setLeavesqty(oo.getQuantity());
//		oo.setCumQtyl(0);
//		oo.setDate(new Date(System.currentTimeMillis()));
//		return oo;
//	}
//
//	public static String getMsgType(String fixData) {
//		MyFIX mf = new MyFIX(fixData);
//		return mf.getTag(35);
//	}
//
//	public static String orderReceivedResponse(OriginOrder oo,
//			String receivedStatus) {
//		MyFIX mf = new MyFIX("OriginOrderResponse",
//				String.valueOf(oo.getTid()), broker_id, 1);
//		mf.setTag(101, receivedStatus);
//		return mf.getFIX();
//	}
//	
	public static OriginOrder FIX2Order(MyFIX mf) {
		OriginOrder oo = new OriginOrder();
		oo.setTid(Integer.parseInt(mf.getTag(49)));
		oo.setFid(Integer.parseInt(mf.getTag(101)));
		oo.setQuantity(Integer.parseInt(mf.getTag(102)));
		oo.setCumQtyl(0);
		oo.setLeavesqty(oo.getQuantity());
		oo.setPrice(Integer.parseInt(mf.getTag(103)));
		oo.setDate(new Date(System.currentTimeMillis()));
		return oo;
	}
}
