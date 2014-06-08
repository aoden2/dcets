/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : MyAddProcess.java
 * 
 * All rights reserved.
 */

import java.util.Hashtable;

public class MyAddProcess {
	public static synchronized void add(int key,
			Hashtable<Integer, MyProcess> processes) {
		if (null == processes.get(key)) {
			processes.put(key, new MyProcess());
		}
	}
}