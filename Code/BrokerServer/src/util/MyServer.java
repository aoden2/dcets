/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : MyServer.java
 * 
 * All rights reserved.
 */
package util;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class MyServer extends Thread {
	ServerSocket server = null;
	Socket socket = null;
	String password = null;
	Hashtable<Integer, MyProcess> processes = null;
	
	public MyServer(int port, String password, Hashtable<Integer, MyProcess> processes) {
		try {
			server = new ServerSocket(port);
			this.password = password;
			this.processes = processes;
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
		}
	}
	
	public void run() {
		while (true) {
			try {
				socket = server.accept();
				MyThread th = new MyThread(socket, password, processes);
				th.start();
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("Error:" + e);
			}
		}
	}
}
