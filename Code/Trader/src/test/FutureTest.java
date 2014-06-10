package test;

import dao.FutureDao;
import dao.FutureDaoImpl;

public class FutureTest {
	public static void main(String[] args) {
		FutureDao fDao =new FutureDaoImpl(); 
		fDao.refreshFutures();
		System.out.println(fDao.getAllFutures(1));
		System.out.println(fDao.getAllFutureName());
		System.out.println(fDao.getFutureByName("name 2"));
		System.out.println(fDao.getFutureByNamePeriod("name 2", "period 3"));
	}
}
