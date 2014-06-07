package dao;

import java.util.List;

import entity.OriginOrder;

public interface OrderDao {
	public List<OriginOrder> getAllOrders();
	public OriginOrder initOriginOrder(int fid, int bid, int quantity, int price, int status);
	public int sendOriginOrder(OriginOrder oo);
}
