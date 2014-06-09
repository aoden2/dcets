package dao;

import java.util.List;
import java.util.ArrayList;

import entity.OriginOrder;
import entity.BrokerInfo;
import entity.TraderOrder;
import util.*;
import dao.BrokerDao;

public class OrderDaoImpl implements OrderDao{
	public List<OriginOrder> getAllOrders(){
		List<OriginOrder> oos = new ArrayList<OriginOrder>();
		return oos;
	}
	public List<TraderOrder> getOrdersByFutureName(String name){
		List<TraderOrder> oos = new ArrayList<TraderOrder>();
		return oos;
	}
	public OriginOrder initOriginOrder(int fid, int bid, int quantity, int price, int status){
		OriginOrder oo = new OriginOrder();
		oo.setFid(fid);
		oo.setBid(bid);
		oo.setQuantity(quantity);
		oo.setPrice(price);
		oo.setStatus(status);
		return oo;
	}
	public String sendOriginOrder(OriginOrder oo){
		String rtn = "";
		BrokerDao bd = new BrokerDaoImpl();
		BrokerInfo broker = bd.getBrokerbyId(oo.getBid());
		MyClient c = null;
			try {
				String data = OriginOrderFIXHelper.OriginOrder2Fix(oo);
				c = new MyClient(broker.getIp(), broker.getPort(), broker.getPass());
				rtn = c.send(data);
			} catch(Exception e) {
				System.out.println("Error : " + e);
			}
		return rtn;
	}

}
