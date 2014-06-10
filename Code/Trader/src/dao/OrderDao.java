package dao;

import java.util.List;

import entity.FinalOrder;
import entity.OriginOrder;
import entity.TraderOrder;

public interface OrderDao {
	public List<OriginOrder> getAllOrders();

	public List<TraderOrder> getOrdersByFutureName(String name);

	public OriginOrder initOriginOrder(int fid, int bid, int quantity,
			int price, int status);

	public String sendOriginOrder(OriginOrder oo);

	//get all final order
	public List<FinalOrder> getAllFinalOrder();
	
	//get my origin order by trader id
	public List<OriginOrder> getMyOriginOrder(int tid);
}