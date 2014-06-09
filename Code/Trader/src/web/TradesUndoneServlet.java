package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TradesUndoneServlet
 */
@WebServlet("/TradesUndoneServlet")
public class TradesUndoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradesUndoneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		try {
			String txt = "";
			for (int i = 0; i < 10; i++) {
				txt = txt
						+ "<tr class=\"odd gradeX\">"
						+ "<td class=\"center\"> 12345</td>"
						+ "<td class=\"center\"> ABC</td>"
						+ "<td class=\"center\"> Gold Swap</td>"
						+ "<td class=\"center\"> SEP14</td>"
						+ "<td class=\"center\"> 1246</td>"
						+ "<td class=\"center\"> 5</td>"
						+ "<td class=\"center\"> Undone</td>"
						+ "<td class=\"center\"><input type=\"button\" id=\"12345\" onclick=\"cancel(this)\" value=\"cancel\"></td>"						
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
