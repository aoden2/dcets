package dao;

import java.util.List;
import java.util.ArrayList;

import entity.OriginOrder;

public class OrderDaoImpl implements OrderDao{
	public List<OriginOrder> getAllOrders(){
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
	public int sendOriginOrder(OriginOrder oo){
		return 0;
	}

}
