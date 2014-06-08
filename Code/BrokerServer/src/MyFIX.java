/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : MyFIX.java
 * 
 * All rights reserved.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class MyFIX {
	LinkedHashMap<String, String> fix = null;
	LinkedHashMap<String, String> con = null;

	public MyFIX(String MsgType, String SenderCompID, String TargetCompID,
			int MsgSeqNum) {
		fix = new LinkedHashMap<String, String>();
		fix.put("8", "FIX.4.2");
		fix.put("9", "0");
		fix.put("35", MsgType);
		fix.put("49", SenderCompID);
		fix.put("56", TargetCompID);
		fix.put("34", Integer.toString(MsgSeqNum));
		fix.put("52", "2014060714:14:14");
		con = new LinkedHashMap<String, String>();
	}

	public MyFIX(String data) {
		fix = new LinkedHashMap<String, String>();
		con = new LinkedHashMap<String, String>();
		int check = data.indexOf("^10=");
		data = data.substring(0, check);
		String[] tags = data.split("^");
		for (int i = 0, j = tags.length; i < j; i++) {
			String[] t = tags[i].split("=");
			setTag(Integer.valueOf(t[0]), t[1]);
		}
	}

	public String getFIX() {
		String content = new String();
		content = parseMap2Str(con);
		fix.put("34", Integer.toString(content.length()));
		fix.put("52", getCurDate());
		content = parseMap2Str(fix) + content;
		int checkSum = content.length() % 256;
		content += "10=" + Integer.toString(checkSum);
		return content;
	}

	public void setTag(int tag, String val) {
		if (tag > 100)
			con.put(Integer.toString(tag), val);
		else
			fix.put(Integer.toString(tag), val);
	}

	public String getTag(int tag) {
		if (tag > 100)
			return con.get(Integer.toString(tag));
		else
			return fix.get(Integer.toString(tag));
	}

	public static String parseMap2Str(LinkedHashMap<String, String> map) {
		String content = "";
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			String key = (String) entry.getKey();
			String val = (String) entry.getValue();
			content += key + "=" + val + "^";
		}
		return content;
	}

	public static String getCurDate() {
		String date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());
		date = formatter.format(curDate);
		return date;
	}

	public static boolean checkData(String data) {
		int check = data.indexOf("^10=");
		if (check == -1)
			return false;
		check += 1;
		String checkSum = data.substring(check);
		String[] t = checkSum.split("=");
		int m = Integer.valueOf(t[1]);
		if (m != (check % 256))
			return false;
		return true;
	}
}
