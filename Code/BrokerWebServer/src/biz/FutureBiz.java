package biz;

import java.util.List;

import dao.FutureDao;
import dao.FutureDaoImpl;
import entity.Future;

public class FutureBiz {
	
	FutureDao fDao = new FutureDaoImpl();
	
	public List<Future> listAllFuture(){
		return fDao.listAllFuture();
	}

}
