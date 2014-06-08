package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

			int id = Integer.parseInt(request.getParameter("id"));
			// 根据类型用ajax方式传送对应类型的电脑列表
			// List<Computer> list =dao.PCList(type);
			// String s = new String(type.getBytes("iso-8859-1"),"utf-8");
			String txt = "<thead>" + "<tr>" + "<th>Period</th>"
					+ "<th>Broker</th>" + "<th>Qty</th>" + "<th>Bid</th>"
					+ "<th>Ask</th>" + "<th>Qty</th>" + "<th>Broker</th>"
					+ "<th>Last</th>" + "</tr>" + "</thead>" + "<tbody>";
			for (int i = 0; i < 10; i++) {
				txt = txt
						+ "<tr class=\"odd gradeX\">"
						+ "<td class=\"center\"> SEP14</td>"
						+ "<td onclick=\"FillOrder(1,1,'Gold','SEP14','ABC',5,1240);\" class=\"center\"> ABC</td>"
						+ "<td onclick=\"FillOrder(1,1,'Gold','SEP14','ABC',5,1240);\" class=\"center\"> 5</td>"
						+ "<td onclick=\"FillOrder(1,1,'Gold','SEP14','ABC',5,1240);\" class=\"center\"> 1240</td>"
						+ "<td onclick=\"FillOrder(1,0,'Gold','SEP14','BC1',10,1246);\"class=\"center\"> 1246</td>"
						+ "<td onclick=\"FillOrder(1,0,'Gold','SEP14','BC1',10,1246);\"class=\"center\"> 10</td>"
						+ "<td onclick=\"FillOrder(1,0,'Gold','SEP14','BC1',10,1246);\"class=\"center\"> BC1</td>"
						+ "<td class=\"center\"> 1241</td>" + "</tr>";
			}
			txt += "</tbody>";
			if (id == -1) {
				txt = "";
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
