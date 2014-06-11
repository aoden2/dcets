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

import dao.BrokerDao;
import dao.BrokerDaoImpl;
import dao.FutureDao;
import dao.FutureDaoImpl;
import dao.OrderDao;
import dao.OrderDaoImpl;
import entity.OriginOrder;

/**
 * Servlet implementation class TradesUndoneServlet
 */
@WebServlet("/TradesUndoneServlet")
public class TradesUndoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	OrderDao dao = new OrderDaoImpl();
	BrokerDao bdao = new BrokerDaoImpl();
	FutureDao fdao = new FutureDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradesUndoneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();

		try {
			String txt = "";
			List<OriginOrder> ls = new ArrayList<>();
			ls = dao.getMyOriginOrder(1);
			for(OriginOrder o : ls){
				int status = o.getStatus();
				if(Math.abs(status) >= 2){
				int id = o.getId();
				int fid = o.getFid();
				int bid = o.getBid();
				String bname = bdao.getBrokerbyId(bid).getName();
				int iid = o.getFid();
				String iname = fdao.getFutureById(iid).getName();
				String period = fdao.getFutureById(iid).getPeriod();
				int price = o.getPrice();
				int qty = o.getQuantity();
				txt = txt
						+ "<tr class=\"odd gradeX\">"
						+ "<td class=\"center\">"+id+"</td>"
						+ "<td class=\"center\">"+bname+"</td>"
						+ "<td class=\"center\">"+iname+"</td>"
						+ "<td class=\"center\">"+period+"</td>"
						+ "<td class=\"center\">"+price+"</td>"
						+ "<td class=\"center\">"+qty+"</td>"
						+ "<td class=\"center\"> Undone</td>"
						+ "<td class=\"center\"><input type=\"button\" onclick=\"Cancel("+id+","+fid+")\" value=\"cancel\"></td>"						
						+ "</tr>";
				}
				
			}			
			out.println(txt);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
