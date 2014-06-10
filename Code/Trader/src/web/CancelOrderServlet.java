package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MyClient;
import util.OriginOrderFIXHelper;
import dao.BrokerDao;
import dao.BrokerDaoImpl;
import dao.FutureDao;
import dao.FutureDaoImpl;
import dao.OrderDao;
import dao.OrderDaoImpl;
import entity.BrokerInfo;
import entity.OriginOrder;

/**
 * Servlet implementation class CancelOrderServlet
 */
@WebServlet("/CancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fid = Integer.parseInt(request.getParameter("fid"));
		int ooid = Integer.parseInt(request.getParameter("id"));
		System.out.println("Im in, canceling order");
		int bid = 2;
		BrokerDao bd = new BrokerDaoImpl();
		BrokerInfo broker = bd.getBrokerbyId(bid);
		MyClient c = null;
			try {
				String data = OriginOrderFIXHelper.revokeOrderFIX(fid, ooid, broker.getId());
				c = new MyClient(broker.getIp(), broker.getPort(), broker.getPass());
				String rtn = c.send(data);
			} catch(Exception e) {
				System.out.println("Error : " + e);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
