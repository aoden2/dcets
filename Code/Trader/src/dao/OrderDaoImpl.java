package dao;

import java.util.ArrayList;
import java.util.List;

import util.BrokerReceiver;
import util.MyClient;
import util.OriginOrderFIXHelper;
import util.WSParser;
import client.httpRequest;
import entity.BrokerInfo;
import entity.FinalOrder;
import entity.OriginOrder;
import entity.TraderOrder;

public class OrderDaoImpl implements OrderDao {
	public List<OriginOrder> getAllOrders() {
		List<OriginOrder> oos = new ArrayList<OriginOrder>();
		return oos;
	}

	public List<TraderOrder> getOrdersByFutureName(String name) {
		System.out.println("enter getOrdersByfutureName");
		int bid = 2;
		List<List<TraderOrder>> oos = new ArrayList<List<TraderOrder>>();
		FutureDao fd = new FutureDaoImpl();
		List<Integer> fids = fd.getFutureByName(name);
		for (int i = 0; i < fids.size(); i++) {
			oos.add(getOrders(fids.get(i), bid));
		}
		System.out.println(oos.get(0).size());
		List<TraderOrder> ans = BrokerReceiver.mergeOrder(oos);
		System.out.println("123333:"+ans.size());
		return ans;

	}

	public OriginOrder initOriginOrder(int fid, int bid, int quantity,
			int price, int status) {
		OriginOrder oo = new OriginOrder();
		oo.setFid(fid);
		oo.setBid(bid);
		oo.setQuantity(quantity);
		oo.setPrice(price);
		oo.setStatus(status);
		return oo;
	}

	public String sendOriginOrder(OriginOrder oo) {
		String rtn = "";
		BrokerDao bd = new BrokerDaoImpl();
		BrokerInfo broker = bd.getBrokerbyId(oo.getBid());
		MyClient c = null;
		try {
			String data = OriginOrderFIXHelper.OriginOrder2Fix(oo);
			System.out.println("data:" + data);
			c = new MyClient(broker.getIp(), broker.getPort(),
			broker.getPass());
			rtn = c.send(data);
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
		return rtn;
	}

	public List<FinalOrder> getAllFinalOrder() {
		String s = httpRequest
				.sendGet(
						"http://59.78.3.25:8080/BrokerWebServer/services/getFinalOrder",
						null);
		List<FinalOrder> ans = WSParser.parseAllFinalOrder(s);
		return ans;
	}

	public List<OriginOrder> getMyOriginOrder(int tid) {
		String s = httpRequest
				.sendPost(
						"http://59.78.3.25:8080/BrokerWebServer/services/getOriginOrder",
						"" + tid);
		return WSParser.parseAllOriginOrder(s, 1);
	}

	private List<TraderOrder> getOrders(int fid, int bid) {
		System.out.println("enter getOrders");
		List<TraderOrder> tos = new ArrayList<TraderOrder>();
		BrokerDao bd = new BrokerDaoImpl();
		BrokerInfo broker = bd.getBrokerbyId(bid);
		System.out.println("IP:" + broker.getIp());
		System.out.println("port:" + broker.getPort());
		MyClient c = null;
		try {
			String data = OriginOrderFIXHelper.queryFutureFIX(fid, bid);
			System.out.println("data:"+data);
			c = new MyClient(broker.getIp(), broker.getPort(), broker.getPass());
			String rtn = c.send(data);
			System.out.println(rtn);
			tos.add(OriginOrderFIXHelper.parseQueryFuture(rtn, fid));
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
		return tos;
	}

}
