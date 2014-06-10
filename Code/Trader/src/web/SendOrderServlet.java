package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BrokerDao;
import dao.BrokerDaoImpl;
import dao.FutureDao;
import dao.FutureDaoImpl;
import dao.OrderDao;
import dao.OrderDaoImpl;
import entity.OriginOrder;

/**
 * Servlet implementation class ProcessOrderServlet
 */
@WebServlet("/SendOrderServlet")
public class SendOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Im in, sending order");
		String opt = request.getParameter("operation");// 0=buy 1=sell
		String item_name = request.getParameter("name");
		String broker_name = request.getParameter("broker");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int price = Integer.parseInt(request.getParameter("price"));
		String period = request.getParameter("period");
		System.out.println("I get all the parameter.");
		FutureDao fd = new FutureDaoImpl();
		BrokerDao bd = new BrokerDaoImpl();
		int fid = fd.getFutureByNamePeriod(item_name, period);
		System.out.println("item_name="+item_name+"period="+period+"fid="+fid);
		int bid = bd.getBrokerIdbyName(broker_name);
		OrderDao od = new OrderDaoImpl();
		OriginOrder oo = null;
		if ("0".equals(opt)) {
			oo = od.initOriginOrder(fid, bid, quantity, price, 3);
		} else if ("1".equals(opt)) {
			oo = od.initOriginOrder(fid, bid, quantity, price, -3);
		}
		od.sendOriginOrder(oo);
	}

}

