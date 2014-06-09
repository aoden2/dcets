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
import entity.BrokerInfo;

/**
 * 拉取当前所有的broker名单
 */
@WebServlet("/BrokerServlet")
public class BrokerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BrokerDao dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrokerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter(); 

        try {            
        	String txt="";
        	List<BrokerInfo> ls = new ArrayList<>();
        	ls = dao.getAllBrokers();
        	for(BrokerInfo b : ls){
        		String name = b.getName();
        		txt = txt
        				+"<option value='"+name+"'>"+name+"</option>";
        	}            
            out.println(txt);
            out.flush();
        } catch(Exception e){
        	e.printStackTrace();
        }
        finally {
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
