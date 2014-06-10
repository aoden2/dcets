package util;

import java.util.ArrayList;
import java.util.List;

import entity.OriginOrder;

public class OriginOrderFIXHelper {
	private static String comp_id = "1";

	public static String OriginOrder2Fix(OriginOrder oo) {
		MyFIX mf = null;
		if (oo.getStatus() == 3) {
			mf = new MyFIX("1", comp_id, String.valueOf(oo.getBid()), 1);
			mf.setTag(101, String.valueOf(oo.getFid()));
			mf.setTag(102, String.valueOf(oo.getQuantity()));
			mf.setTag(103, String.valueOf(oo.getPrice()));
			mf.setTag(104, String.valueOf(oo.getStatus()));
		} else if (oo.getStatus() == -3) {
			mf = new MyFIX("2", comp_id, String.valueOf(oo.getBid()), 1);
			mf.setTag(101, String.valueOf(oo.getFid()));
			mf.setTag(102, String.valueOf(oo.getQuantity()));
			mf.setTag(103, String.valueOf(oo.getPrice()));
			mf.setTag(104, String.valueOf(oo.getStatus()));
		}
		return mf.getFIX();
	}

	public static String queryFutureFIX(int fid, int bid) {
		MyFIX mf = new MyFIX("1", comp_id, String.valueOf(bid), 1);
		mf.setTag(101, String.valueOf(fid));
		return mf.getFIX();
	}

	public static List<OriginOrder> parseOriginOrder(String fixData) {
		List<OriginOrder> oos = new ArrayList<OriginOrder>();
		MyFIX mf = new MyFIX(fixData);
		int init_tag = 100;
		while (mf.getTag(init_tag) != null) {
			OriginOrder oo = new OriginOrder();
			oo.setFid(Integer.parseInt(mf.getTag((init_tag + 1))));
			oo.setQuantity(Integer.parseInt(mf.getTag((init_tag + 2))));
			oo.setPrice(Integer.parseInt(mf.getTag((init_tag + 3))));
			oo.setStatus(Integer.parseInt(mf.getTag((init_tag + 4))));
			oo.setBid(Integer.parseInt(mf.getTag((init_tag + 5))));
			init_tag = init_tag + 10;
			oos.add(oo);
		}
		return oos;
	}
}
