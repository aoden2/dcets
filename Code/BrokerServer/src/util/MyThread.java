package util;
/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-6
 * Project  : SocketServer
 * Filename : MyThread.java
 * 
 * All rights reserved.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyThread extends Thread {
	Socket socket = null;
	PrintWriter os = null;
	BufferedReader is = null;
	String password = null;

	public MyThread(Socket socket, String password) {
		this.socket = socket;
		this.password = password;
		try {
			this.os = new PrintWriter(this.socket.getOutputStream());
			this.is = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	public void run() {
		try {
			String data = is.readLine();
			data = MyAES.decrypt(data, password);
			String ret = MyProcess.proc(data);
			ret = MyAES.encrypt(ret, password);
			os.println(ret);
			os.flush();
			is.close();
			os.close();
			socket.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
