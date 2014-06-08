/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : Main.java
 * 
 * All rights reserved.
 */
import java.util.Hashtable;

import util.MyProcess;
import util.MyServer;

public class Main {
	public static void main(String args[]) {
		try {
			Hashtable<Integer, MyProcess> processes = new Hashtable<Integer, MyProcess>();
			MyServer server = new MyServer(4700, "password", processes);
			server.start();
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}
}
