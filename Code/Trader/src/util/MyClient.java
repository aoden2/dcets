package util;

/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-6
 * Project  : SocketClient
 * Filename : MyClient.java
 * 
 * All rights reserved.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {
	Socket socket = null;
	PrintWriter os = null;
	BufferedReader is = null;
	String password = null;
	
	public MyClient(String host, int port, String password) {
		try {
			socket = new Socket(host, port);
			this.password = password;
			os = new PrintWriter(socket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}
	
	public String send(String data) {
		String ret = null;
		try {
			data = MyAES.encrypt(data, password);
			os.println(data);
			os.flush();
			ret = is.readLine();
			ret = MyAES.decrypt(ret, password);
			os.close();
			is.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
		return ret;
	}
}
