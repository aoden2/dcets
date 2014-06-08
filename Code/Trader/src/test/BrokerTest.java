package test;

import java.util.ArrayList;
import java.util.List;

import util.BrokerReceiver;
import entity.TraderOrder;

public class BrokerTest {

	public static void main(String[] args) {
		TraderOrder t1 = new TraderOrder(123, 3, "SEP14", "ABC", 5, 1240, 0, 1241);
		TraderOrder t2 = new TraderOrder(123, 3, "SEP14", "ABC", 5, 1242, 0, 1241);
		TraderOrder t3 = new TraderOrder(123, -3, "OCT14", "ABC", 5, 0, 1246, 1241);
		TraderOrder t4 = new TraderOrder(123, 3, "SEP14", "BBC", 5, 1241, 0, 1241);
		TraderOrder t5 = new TraderOrder(123, -3, "SEP14", "BBC", 5, 0, 1246, 1241);
		TraderOrder t6 = new TraderOrder(123, -3, "OCT14", "BBC", 5, 0, 1245, 1241);
		List<TraderOrder> l1 = new ArrayList<>();
		List<TraderOrder> l2 = new ArrayList<>();
		l1.add(t1);
		l1.add(t2);
		l1.add(t3);
		l2.add(t4);
		l2.add(t5);
		l2.add(t6);
		List<List<TraderOrder>> l = new ArrayList<>();
		l.add(l1);
		l.add(l2);
		List<TraderOrder> ans = BrokerReceiver.mergeOrder(l);
		for (int i =0; i<ans.size(); i++){
			System.out.println(ans.get(i).getPeriod() + " " + ans.get(i).getBroker() + " " + ans.get(i).getType() + " " + ans.get(i).getBid() + " " + ans.get(i).getAsk());
		}

	}

}
