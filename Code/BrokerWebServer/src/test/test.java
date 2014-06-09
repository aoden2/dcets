package test;

import java.util.List;

import util.WSParser;
import Client.httpRequest;
import entity.Future;

public class test {

	public static void main(String[] args) {
		String x = httpRequest.sendGet("http://localhost:8080/BrokerWebServer/services/getFuture", null);
		System.out.println(x);
		List<Future> l = WSParser.parseAll(x);
		System.out.println(l.toString());
	}

}
