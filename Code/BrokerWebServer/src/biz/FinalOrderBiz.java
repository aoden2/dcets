package biz;

import java.util.List;

import dao.FinalOrderDao;
import dao.FinalOrderDaoImpl;
import entity.FinalOrder;

public class FinalOrderBiz {
	
	FinalOrderDao fDao = new FinalOrderDaoImpl();
	
	public List<FinalOrder> listAllFinalOrder(){
		return fDao.listAllFinalOrder();
	}

}
