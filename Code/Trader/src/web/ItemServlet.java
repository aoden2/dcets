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

import dao.FutureDao;
import dao.FutureDaoImpl;

/**
 * 获取商品名列表
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FutureDao dao = new FutureDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("Im at item servlet!");
        PrintWriter out = response.getWriter(); 

        try {    
        	String txt = "<option value=''>Null</option>";
        	List<String> ls = new ArrayList<>();
        	ls = dao.getAllFutureName();
        	for(String s : ls){
        		txt=txt
        				+"<option value='"+s+"'>"+s+"</option>";
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
