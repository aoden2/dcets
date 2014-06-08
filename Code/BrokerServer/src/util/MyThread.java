/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : MyThread.java
 * 
 * All rights reserved.
 */
package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyThread extends Thread {
	Socket socket = null;
	PrintWriter os = null;
	BufferedReader is = null;
	String password = null;
	MyProcess process = null;

	public MyThread(Socket socket, String password, MyProcess process) {
		this.socket = socket;
		this.password = password;
		this.process = process;
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
			
			String ret = process.procData(data);
			
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
