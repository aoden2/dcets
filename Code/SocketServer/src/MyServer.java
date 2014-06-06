/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-6
 * Project  : SocketServer
 * Filename : MyServer.java
 * 
 * All rights reserved.
 */
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer extends Thread {
	ServerSocket server = null;
	Socket socket = null;
	String password = null;
	
	public MyServer(int port, String password) {
		try {
			server = new ServerSocket(port);
			this.password = password;
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
		}
	}
	
	public void run() {
		while (true) {
			try {
				socket = server.accept();
				MyThread th = new MyThread(socket, password);
				th.start();
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("Error:" + e);
			}
		}
	}
}
