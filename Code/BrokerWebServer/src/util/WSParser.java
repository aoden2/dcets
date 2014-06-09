package util;

import java.util.ArrayList;
import java.util.List;

import entity.Future;

public class WSParser {
	
	public static Future sToFuture(String s){
		String ids[] = s.split(",");
		Future f = new Future(Integer.parseInt(ids[0]), ids[1], ids[2], ids[3]);
		return f;
	}

	public static List<Future> parseAll(String s){
		List<Future> l = new ArrayList<>();
		String str[] = s.split("\\|");
		for (int i=1; i<str.length; i++)
			l.add(sToFuture(str[i]));
		return l;
	}

}
