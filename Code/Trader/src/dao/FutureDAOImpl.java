package dao;

import java.util.ArrayList;
import java.util.List;

import entity.BrokerInfo;
import entity.Future;
import util.*;

public class FutureDAOImpl implements FutureDAO{
	public List<Future> getAllFutures(int bid){
		List<Future> futures = new ArrayList<Future>();
		BrokerDao bd = new BrokerDaoImpl();
		BrokerInfo broker = bd.getBrokerbyId(bid);
		
		String future_str = HttpHelper.SendHttpRequest("get", broker.getIp()+":8080/BrokerWebServer/services/getFuture", null);
		
		return futures;
	}
}
