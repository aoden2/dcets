package dao;

import java.util.List;

import util.WSParser;
import client.httpRequest;
import entity.FinalOrder;
import entity.OriginOrder;

public class OrderDao {
	public List<FinalOrder> getAllFinalOrder() {
		String s = httpRequest
				.sendGet(
						"http://59.78.3.25:8080/BrokerWebServer/services/getFinalOrder",
						null);
		List<FinalOrder> ans = WSParser.parseAllFinalOrder(s);
		return ans;
	}

	public List<OriginOrder> getMyOriginOrder(int tid) {
		String s = httpRequest
				.sendGet("http://59.78.3.25:8080/BrokerWebServer/services/getAllOriginOrder",null);
		return WSParser.parseAllOriginOrder(s, 1);
	}
}
