package test;

import java.util.List;

import util.WSParser;
import client.httpRequest;
import entity.Future;


public class test {

	public static void main(String[] args) {
		String x = httpRequest.sendGet("http://localhost:8080/BrokerWebServer/services/getFuture", null);
		System.out.println(x);
		List<Future> l1 = WSParser.parseAllFuture(x);
		System.out.println(l1.toString());
		
		System.out.println("--------------------------------");
		/*		
		String y = httpRequest.sendGet("http://localhost:8080/BrokerWebServer/services/getFinalOrder", null);
		System.out.println(y);
		List<FinalOrder> l2 = WSParser.parseAllFinalOrder(y);
		System.out.println(l2.toString());*/
		//String z = httpRequest.sendPost("http://localhost:8080/BrokerWebServer/services/getOriginOrder", "2");
		//System.out.println(z);
	}

}
