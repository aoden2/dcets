package dao;

import java.util.List;

import entity.OriginOrder;

public interface OrderDao {
	public List<OriginOrder> getAllOrders();
	public List<OriginOrder> getOrdersByFutureId(int fid);
	public OriginOrder initOriginOrder(int fid, int bid, int quantity, int price, int status);
	public String sendOriginOrder(OriginOrder oo);
}
