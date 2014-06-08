package util;

import java.util.ArrayList;
import java.util.List;

import entity.TraderOrder;

public class BrokerReceiver {

	private static List<TraderOrder> sortByPeriod(List<TraderOrder> tl, int l,
			int r) {
		if (l >= r)
			return tl;
		List<TraderOrder> ans = tl;
		int i = l;
		int j = r;
		String k = ans.get((l + r) / 2).getPeriod();
		TraderOrder temp = null;
		while (i < j) {
			while (ans.get(i).getPeriod().compareTo(k) < 0)
				i++;
			while (ans.get(j).getPeriod().compareTo(k) > 0)
				j--;
			if (i < j) {
				temp = ans.get(i);
				ans.set(i, ans.get(j));
				ans.set(j, temp);
				i++;
				j--;
			}
		}
		ans = sortByPeriod(ans, l, j);
		ans = sortByPeriod(ans, i, r);
		return ans;
	}

	/*
	 * Select 5 sell & 5 buy
	 */
	private static List<TraderOrder> selectOrder(List<TraderOrder> l) {
		List<TraderOrder> ans = new ArrayList<>();
		String nowPeriod = "";
		int i = 0;
		int j = 0;
		while (i < l.size()) {
			int n = l.size();
			nowPeriod = l.get(i).getPeriod();
			while ((j < n) && nowPeriod.equalsIgnoreCase(l.get(j).getPeriod()))
				j++;
			
			// Get 5 or less Highest Buy
			int max;
			for (int x = 0; x < 5; x++) {
				max = -1;
				for (int k = i; k < j; k++)
					if (3 == l.get(k).getType()) // Buy
						if ((-1 == max) || (l.get(k).getBid()>l.get(max).getBid()))
							max = k;
				if (-1 != max){
					ans.add(l.get(max));
					l.remove(max);
					j--;
				}
			}
			
			// Get 5 or less Cheapest Sell
			int min;
			for (int x = 0; x < 5; x++) {
				min = -1;
				for (int k = i; k < j; k++)
					if (-3 == l.get(k).getType()) // Sell
						if ((-1 == min) || (l.get(k).getAsk() < l.get(min).getAsk()))
							min = k;
				if (-1 != min){
					ans.add(l.get(min));
					l.remove(min);
					j--;
				}
			}
			
			i = j;
		}
		return ans;
	}

	public static List<TraderOrder> mergeOrder(List<List<TraderOrder>> l) {
		List<TraderOrder> ans = new ArrayList<>();
		for (int i = 0; i < l.size(); i++)
			ans.addAll(l.get(i));
		ans = sortByPeriod(ans, 0, ans.size() - 1);
		ans = selectOrder(ans);
		return ans;
	}

}
