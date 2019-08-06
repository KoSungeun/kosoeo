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
		while (in != null) {
			try {
				request = URLDecoder.decode(in.readLine(),"UTF-8");
				if(su.requestSplit(request, 0).equals("join")) {
					System.out.println(su.requestSplit(request, 1));
					break;
				}
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
