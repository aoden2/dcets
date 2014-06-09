package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.OrderDaoImpl;
import entity.OriginOrder;

/**
 * Servlet implementation class ProcessOrderServlet
 */
@WebServlet("/ProcessOrderServlet")
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessOrderServlet() {
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
		String opt = request.getParameter("operation");// 0=buy 1=sell
		String item_name = request.getParameter("name");
		String broker_name = request.getParameter("broker");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int price = Integer.parseInt(request.getParameter("quantity"));
		String period = request.getParameter("period");
		// TODO
		int fid = 0;
		int bid = 0;
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
