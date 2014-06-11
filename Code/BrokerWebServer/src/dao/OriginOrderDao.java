package dao;

import java.util.List;

import entity.OriginOrder;

public interface OriginOrderDao {
	
	public List<OriginOrder> listOriginOrder(int tid);

	public List<OriginOrder> listOriginOrder();

}
