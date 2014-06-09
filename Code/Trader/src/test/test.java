package test;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MyClient;
import util.OriginOrderFIXHelper;
import dao.OrderDao;
import dao.OrderDaoImpl;
import entity.OriginOrder;



/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyClient c = null;
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				
				OriginOrder oo = null;
				OriginOrder oo2 = null;
				OrderDao od = new OrderDaoImpl();
				Random random1 = new Random();
				System.out.println((random1.nextInt())%1000);
				oo = od.initOriginOrder(1, 1, Math.abs((random1.nextInt())%1000)+1, Math.abs((random1.nextInt())%1000)+1, 3);
				oo2 = od.initOriginOrder(1, 1, Math.abs((random1.nextInt())%1000)+1, Math.abs((random1.nextInt())%1000)+1, -3);
				
				String data = OriginOrderFIXHelper.OriginOrder2Fix(oo);
				String data2 = OriginOrderFIXHelper.OriginOrder2Fix(oo2);
				c = new MyClient("59.78.3.10", 4700, "ldsinkzzzzzzzzz");
				
				//MyFIX mf = new MyFIX("MsgType", "10010", "10086", 2);
				//mf.setTag(102, "102");
				//mf.setTag(103, "103");
				//mf.setTag(104, "3");
				//mf.setTag(101, "101");
				//data = mf.getFIX();
				System.out.println(data);
				System.out.println(data2);
				System.out.println(c.send(data));
				System.out.println(c.send(data2));
				Thread.sleep(2000);
			} catch(Exception e) {
				System.out.println("Error : " + e);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
