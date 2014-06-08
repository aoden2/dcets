import util.MyServer;

public class Main {
	public static void main(String args[]) {
		try {
			MyServer server = new MyServer(4700, "password");
			server.start();
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}
}
