package web;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import biz.OriginOrderBiz;
import entity.OriginOrder;

@Path("/getAllOriginOrder")
public class GetAllOriginOrder {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getOriginOrder() {
		OriginOrderBiz oBiz = new OriginOrderBiz();
		List<OriginOrder> l = oBiz.listOriginOrder();
		String ans = "";
		for (int i = 0; i < l.size(); i++)
			ans = ans + l.get(i).toString();
		return ans;

	}
}
