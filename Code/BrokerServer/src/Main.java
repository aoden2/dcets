/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : Main.java
 * 
 * All rights reserved.
 */

import java.util.Hashtable;

public class Main {
	public static void main(String args[]) {
		try {
			Hashtable<Integer, MyProcess> processes = new Hashtable<Integer, MyProcess>();
			// debug
			String test = "test";
			System.out.println(test);
			test = MyAES.encrypt(test, "aaaaa");
			System.out.println(test);
			test = MyAES.decrypt(test, "aaaaa");
			System.out.println(test);
			// for each trader
			MyServer server4Huaxia = new MyServer(4700, "ldsink",
					processes);
			server4Huaxia.start();
			// MyServer server4Guangda = new MyServer(4701, "GuangDaZhengQuan",
			// processes);
			// server4Guangda.start();
			// MyServer server4GuoTai = new MyServer(4701, "GuoTaiZhengQuan",
			// processes);
			// server4GuoTai.start();
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}
}
