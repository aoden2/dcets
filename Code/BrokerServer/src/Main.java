/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : Main.java
 * 
 * All rights reserved.
 */
import util.MyProcess;
import util.MyServer;

public class Main {
	public static void main(String args[]) {
		try {
			MyProcess process = new MyProcess();
			MyServer server = new MyServer(4700, "password", process);
			server.start();
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}
}
