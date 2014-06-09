package biz;

import java.util.List;

import dao.OriginOrderDao;
import dao.OriginOrderDaoImpl;
import entity.OriginOrder;

public class OriginOrderBiz {
	
	OriginOrderDao oDao = new OriginOrderDaoImpl();
	
	public List<OriginOrder> listOriginOrder(int tid){
		System.out.println("Im at Biz");
		return oDao.listOriginOrder(tid);
	}

}
