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
		
		String response;
		String menu;
		String id;
		ChatWin cw = null;
		while (in != null) {
			
			try {
				response = URLDecoder.decode(in.readLine(),"UTF-8");
				menu = su.requestSplit(response, 0);
				if(menu.equals("join")) {
					System.out.println(su.requestSplit(response, 1));
					break;
				} else if(menu.equals("login")) {
					id = su.requestSplit(response, 1);
					if(!id.equals("null")) {
						response = su.requestSplit(response, 2);
						cw = new ChatWin(socket, id);
					} else {
						System.out.println(su.requestSplit(response, 2));
						break;
					}	
				}
				if(response.startsWith("/")) {
					String userId = su.requestSplit(response, 2);
					if(su.requestSplit(response, 1).equals("invite")) {
						if(!cw.invite) {
							cw.invite = true;
							cw.userId = userId;
							System.out.println(su.requestSplit(response, 3) +"/"+ su.requestSplit(response, 4));
						} else {
							cw.out.println("/response/" +userId + "/busy");
						}
					}
				} else {
					System.out.println(response);
				}
				
			} catch (java.net.SocketException ne) {
				System.out.println("접속이 해제됐습니다");
				break;
			} catch (IOException e) {
				System.out.println("접속이 해제됐습니다");
			} catch (NullPointerException e) {
				System.out.println("접속이 해제됐습니다");
			}

		}
		try {
			in.close();
		} catch (IOException e) {
			System.out.println("예외3:"+e);
		}
	}
	

	
	
}
