public class Main {
	public static void main(String args[]) {
		MyClient c = null;
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				String data = null;//br.readLine();
				c = new MyClient("127.0.0.1", 4700, "huaXiaZhengQuan");
				MyFIX mf = new MyFIX("MsgType", "10010", "10086", 2);
				mf.setTag(102, "102");
				mf.setTag(103, "103");
				mf.setTag(104, "3");
				mf.setTag(101, "101");
				data = mf.getFIX();
				System.out.println(c.send(data));
				Thread.sleep(2000);
			} catch(Exception e) {
				System.out.println("Error : " + e);
			}
		}
	}
}
