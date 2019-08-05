import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer4 {

	ServerSocket serverSocket = null;
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	String s = "";

	public MultiServer4() {

	}

	public void init() {

		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("서버가 시작되었습니다.");

			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + ":" + socket.getPort());

			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (in != null) {
				s = in.readLine();
				if (s == null)
					break;
				if (s.equalsIgnoreCase("q")) {
					break;
				}
				System.out.println(s);
				//out.println(s);
				sendAllMsg(s);
			}

			System.out.println("Bye...");

		} catch (IOException e) {
			System.out.println("예외1:" + e);
//			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();

				socket.close();
				serverSocket.close();
			} catch (IOException e) {
				System.out.println("예외2:" + e);
//				e.printStackTrace();
			}
		}

	}

	public void sendAllMsg(String msg) {
		try {
			out.println(msg);
		} catch (Exception e) {
			System.out.println("예외:" + e);
		}
	}

	public static void main(String[] args) {
		// 서버객체 생성
		MultiServer4 ms = new MultiServer4();
		ms.init();
	}

}
