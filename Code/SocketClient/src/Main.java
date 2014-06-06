import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) {
		MyClient c = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				String data = br.readLine();
				c = new MyClient("127.0.0.1", 4700, "password");
				System.out.println(c.send(data));
			} catch(Exception e) {
				System.out.println("Error : " + e);
			}
		}
	}
}
