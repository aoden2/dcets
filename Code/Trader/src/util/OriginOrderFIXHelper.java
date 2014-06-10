package util;

import java.util.ArrayList;
import java.util.List;

import dao.BrokerDao;
import dao.BrokerDaoImpl;
import dao.FutureDao;
import dao.FutureDaoImpl;
import entity.BrokerInfo;
import entity.Future;
import entity.OriginOrder;
import entity.TraderOrder;

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
		MyFIX mf = new MyFIX("4", comp_id, String.valueOf(bid), 1);
		mf.setTag(101, String.valueOf(fid));
		return mf.getFIX();
	}

	public static TraderOrder parseQueryFuture(String fixData, int fid) {
		MyFIX mf = new MyFIX(fixData);
		TraderOrder to = new TraderOrder();
		BrokerDao bd = new BrokerDaoImpl();
		FutureDao fd = new FutureDaoImpl();
		BrokerInfo broker = bd.getBrokerbyId(Integer.valueOf(mf.getTag(49)));
		Future future = fd.getFutureById(fid);
		to.setBrokerBuy(broker.getName());
		to.setBrokerSell(broker.getName());
		if ("-1".equals(mf.getTag(101)) == false) {
			to.setBid(Integer.valueOf(mf.getTag(101)));
			to.setQtyBuy(Integer.valueOf(mf.getTag(102)));
		} else {
			to.setBid(-1);
			to.setQtyBuy(-1);
		}
		if ("-1".equals(mf.getTag(103)) == false) {
			to.setAsk(Integer.valueOf(mf.getTag(103)));
			to.setQtySell(Integer.valueOf(mf.getTag(104)));
		} else {
			to.setAsk(-1);
			to.setQtySell(-1);
		}
		to.setFid(future.getId());
		to.setName(future.getName());
		to.setPeriod(future.getPeriod());
		to.setLast(Integer.valueOf(mf.getTag(105)));
		return to;
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
