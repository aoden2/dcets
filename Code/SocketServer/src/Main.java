public class Main {
	public static void main(String args[]) {
		try {
			MyServer server = new MyServer(4700, "password");
			server.start();
			// 处理信息的函数为  MyProcess 中的  proc
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}
}
