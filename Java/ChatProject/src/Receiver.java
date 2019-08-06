import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLDecoder;

public class Receiver extends Thread {
	Socket socket;
	BufferedReader in = null;
	StringUtil su = new StringUtil();
	
	//Socket을 매개변수로 받는 생성자
	public Receiver(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("예외1:"+e);
		}
	}
	//run()메소드 재정의
	@Override
	public void run() {
		String request;
		String menu;
		String id;
		while (in != null) {
			try {
				request = URLDecoder.decode(in.readLine(),"UTF-8");
				menu = su.requestSplit(request, 0);
				if(menu.equals("join")) {
					System.out.println(su.requestSplit(request, 1));
					break;
				} else if(menu.equals("login")) {
					id = su.requestSplit(request, 1);
					if(!id.equals("null")) {
						request = su.requestSplit(request, 2);
						new ChatWin(socket, id);
					} else {
						System.out.println(su.requestSplit(request, 2));
						break;
					}	
				}
				System.out.println(request);
			} catch (java.net.SocketException ne) {
				break;
			} catch (IOException e) {
				System.out.println("예외2:"+e);
			}

		}
		try {
			in.close();
		} catch (IOException e) {
			System.out.println("예외3:"+e);
		}
	}
	

	
	
}
