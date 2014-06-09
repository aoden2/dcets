package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.FinalOrder;
import entity.Future;
import entity.OriginOrder;

public class WSParser {

	public static Future sToFuture(String s) {
		String ids[] = s.split(",");
		Future f = new Future(Integer.parseInt(ids[0]), ids[1], ids[2], ids[3]);
		return f;
	}

	public static FinalOrder sToFO(String s) {
		String ids[] = s.split(",");
		FinalOrder fo = new FinalOrder(Integer.parseInt(ids[0]),
				Integer.parseInt(ids[1]), Integer.parseInt(ids[2]),
				Integer.parseInt(ids[3]), Integer.parseInt(ids[4]),
				Integer.parseInt(ids[5]));
		return fo;
	}

	public static OriginOrder sToOO(String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		String ids[] = s.split(",");
		OriginOrder oo = null;
		try {
			oo = new OriginOrder(Integer.parseInt(ids[0]),
					Integer.parseInt(ids[1]), Integer.parseInt(ids[2]),
					Integer.parseInt(ids[3]), Integer.parseInt(ids[4]),
					Integer.parseInt(ids[5]), Integer.parseInt(ids[6]),
					sdf.parse(ids[7]), Integer.parseInt(ids[8]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return oo;
	}

	public static List<Future> parseAllFuture(String s) {
		List<Future> l = new ArrayList<>();
		String str[] = s.split("\\|");
		for (int i = 1; i < str.length; i++)
			l.add(sToFuture(str[i]));
		return l;
	}

	public static List<FinalOrder> parseAllFinalOrder(String s) {
		List<FinalOrder> l = new ArrayList<>();
		String str[] = s.split("\\|");
		for (int i = 1; i < str.length; i++)
			l.add(sToFO(str[i]));
		return l;
	}

	public static List<OriginOrder> parseAllOriginOrder(String s) {
		List<OriginOrder> l = new ArrayList<>();
		String str[] = s.split("\\|");
		for (int i = 1; i < str.length; i++)
			l.add(sToOO(str[i]));
		return l;
	}

}
