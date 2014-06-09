package web;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entity.OriginOrder;
import biz.OriginOrderBiz;

@Path("/getOriginOrder")
public class GetOriginOrder {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getOriginOrder(String param) {
		OriginOrderBiz oBiz = new OriginOrderBiz();
		List<OriginOrder> l = oBiz.listOriginOrder(Integer.parseInt(param));
		String ans = "";
		for (int i = 0; i < l.size(); i++)
			ans = ans + l.get(i).toString();
		return ans;

	}
}
