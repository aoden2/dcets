package test;

import dao.OrderDao;
import dao.OrderDaoImpl;

public class UserTest {

	public static void main(String[] args) {
		OrderDao oDao = new OrderDaoImpl();
		System.out.println(oDao.getMyOriginOrder(1).get(0).getFid());
		System.out.println(oDao.getMyOriginOrder(1).get(1).getFid());
		System.out.println(oDao.getMyOriginOrder(1).get(2).getFid());
		System.out.println(oDao.getMyOriginOrder(1).get(3).getFid());

	}

}
