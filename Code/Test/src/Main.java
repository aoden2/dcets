/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : Main.java
 * 
 * All rights reserved.
 */

public class Main {
	public static void main(String args[]) {
		try {
			String test = "test";
			System.out.println(test);
			test = MyAES.encrypt(test, "aaaaa");
			System.out.println(test);
			test = MyAES.decrypt(test, "aaaaa");
			System.out.println(test);
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}
}
