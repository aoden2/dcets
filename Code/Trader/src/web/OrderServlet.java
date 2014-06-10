package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.OrderDaoImpl;
import entity.TraderOrder;

/**
 * 根据商品名拉取各period的行情
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDao dao = new OrderDaoImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		try {

			String name = (String)request.getParameter("name");
			List<TraderOrder> ls = new ArrayList<>();
			String txt="<thead>" + "<tr>" + "<th>Period</th>"
					+ "<th>Broker</th>" + "<th>Qty</th>" + "<th>Bid</th>"
					+ "<th>Ask</th>" + "<th>Qty</th>" + "<th>Broker</th>"
					+ "<th>Last</th>" + "</tr>" + "</thead>" + "<tbody>";
			if(!name.equals("")){
				ls=dao.getOrdersByFutureName(name);
				for(TraderOrder o : ls){					
					String prd = o.getPeriod(); 
					String brk1 = o.getBrokerBuy();
					String brk2 = o.getBrokerSell();
					int qty1 = o.getQtyBuy();
					int qty2 = o.getQtySell();
					int bid = o.getBid();
					int ask = o.getAsk();
					int last = o.getLast();
					String calljs1 = "\"FillOrder(1,'"+name+"','"+prd+"','"+brk1+"',"+qty1+","+bid+")\"";
					String calljs2 = "\"FillOrder(0,'"+name+"','"+prd+"','"+brk2+"',"+qty2+","+ask+")\"";
					txt = txt
							+ "<tr class=\"odd gradeX\">"
							+ "<td class=\"center\">"+ prd +"</td>"
							+ "<td onclick="+calljs1+" class=\"center\">"+brk1+"</td>"
							+ "<td onclick="+calljs1+" class=\"center\">"+qty1+"</td>"
							+ "<td onclick="+calljs1+" class=\"center\">"+bid+"</td>"
							+ "<td onclick="+calljs2+" class=\"center\">"+ask+"</td>"
							+ "<td onclick="+calljs2+" class=\"center\">"+qty2+"</td>"
							+ "<td onclick="+calljs2+" class=\"center\">"+brk2+"</td>"
							+ "<td class=\"center\">"+last+"</td></tr>";
				}
			}

			txt += "</tbody>";			
			out.println(txt);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
