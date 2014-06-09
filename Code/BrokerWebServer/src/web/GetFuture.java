package web;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import biz.FutureBiz;
import entity.Future;

@Path("/getFuture")
public class GetFuture {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllFuture() {
		FutureBiz fBiz = new FutureBiz();
		List<Future> fl = fBiz.listAllFuture();
		String ans = "";
		for (int i=0; i<fl.size(); i++)
			ans = ans + fl.get(i).toString();
		return ans;
	}
}
