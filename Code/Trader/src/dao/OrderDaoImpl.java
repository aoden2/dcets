package dao;

import java.util.List;
import java.util.ArrayList;

import entity.OriginOrder;
import util.*;

public class OrderDaoImpl implements OrderDao{
	public List<OriginOrder> getAllOrders(){
		List<OriginOrder> oos = new ArrayList<OriginOrder>();
		return oos;
	}
	public List<OriginOrder> getOrdersByFutureId(int fid){
		List<OriginOrder> oos = new ArrayList<OriginOrder>();
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
		MyClient c = null;
			try {
				String data = OriginOrderFIXHelper.OriginOrder2Fix(oo);
				c = new MyClient("127.0.0.1", 4700, "password");
				rtn = c.send(data);
			} catch(Exception e) {
				System.out.println("Error : " + e);
			}
		return rtn;
	}

}
