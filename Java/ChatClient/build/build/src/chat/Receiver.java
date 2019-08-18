package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.concurrent.Callable;

public class Receiver implements Callable<String> {
	Socket socket;
	BufferedReader in = null;
	StringUtil su = new StringUtil();

	// Socket을 매개변수로 받는 생성자
	public Receiver(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("예외1:" + e);
		}
	}

	@Override
	public String call() {
		String response = null;
		while (in != null) {
			try {
				response = URLDecoder.decode(in.readLine(), "UTF-8");
				String menu;
				menu = su.rSplit(response, 0);
				if (menu.equals("join")) {
					break;
				} else if (menu.equals("login")) {
					break;
				}
			} catch (java.net.SocketException ne) {
				System.out.println("접속이 해제됐습니다");
				break;
			} catch (IOException e) {
				System.out.println("접속이 해제됐습니다");
				break;
			} catch (NullPointerException e) {
				System.out.println("접속이 해제됐습니다");
				break;
			}
		}
		return response;

	}

}
