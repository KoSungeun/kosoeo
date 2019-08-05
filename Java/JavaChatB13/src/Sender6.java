import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender6 extends Thread {

	Socket socket;
	PrintWriter out = null;
	String name;

	public Sender6(Socket socket, String name) {
		this.socket = socket;

		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			this.name = name;
		} catch (IOException e) {
			System.out.println("예외S3:" + e);
		}

	}

	@Override
	public void run() {
		Scanner s = new Scanner(System.in);

		try {
			// 서버에 입력한 사용자 이름을 보내준다.
			out.println(name);
			while (out != null) {
				try {
					String s2 = s.nextLine();
					if (s2.equalsIgnoreCase("q")) {
						out.println(s2);
						break;
					} else {
						out.println(name + "=>" + s2);
					}

				} catch (Exception e) {
					System.out.println("예외S1:" + e);
				}

			}
			out.close();
			socket.close();
			s.close();
		} catch (Exception e) {
			System.out.println("예외S2:" + e);
		}

	}

}
