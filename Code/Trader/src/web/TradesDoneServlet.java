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
import entity.FinalOrder;

/**
 * Servlet implementation class TradesDoneServlet
 */
@WebServlet("/TradesDoneServlet")
public class TradesDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	OrderDao dao = new OrderDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradesDoneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		try {

			//int id = Integer.parseInt(request.getParameter("id"));
			// 根据类型用ajax方式传送对应类型的电脑列表
			List<FinalOrder> ls = new ArrayList<>();
			ls = dao.getAllFinalOrder();
			// String s = new String(type.getBytes("iso-8859-1"),"utf-8");
			String txt = "";
			for (FinalOrder f : ls) {
				int id = f.getId();
				int qty = f.getQuantity();
				int price = f.getPrice();
				int bid = f.getObid();
				int sid = f.getOsid();
				
				txt = txt
						+ "<tr class=\"odd gradeX\">"
						+ "<td class=\"center\">"+ id+"</td>"
						+ "<td class=\"center\">"+price+"</td>"
						+ "<td class=\"center\">"+qty+"</td>"
						+ "<td class=\"center\">paid</td>"
						+ "<td class=\"center\">" +bid+"</td>"
						+ "<td class=\"center\">" +sid+"</td>"						
						+ "</tr>";
			}
			// String t = new String(txt.getBytes("utf-8"),"iso-8859-1");
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
