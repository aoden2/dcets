package web;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import biz.FinalOrderBiz;
import entity.FinalOrder;

@Path("/getFinalOrder")
public class GetFinalOrder {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllFO() {
		FinalOrderBiz fBiz = new FinalOrderBiz();
		List<FinalOrder> fl = fBiz.listAllFinalOrder();
		String ans = "";
		for (int i=0; i<fl.size(); i++)
			ans = ans + fl.get(i).toString();
		return ans;
	}
}
