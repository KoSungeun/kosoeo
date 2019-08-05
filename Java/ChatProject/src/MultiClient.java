import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.server.ServerCloneException;
import java.util.Scanner;

import javax.xml.ws.handler.MessageContext.Scope;

public class MultiClient {

	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		MultiClient m = new MultiClient();
		
		try {
			Socket socket = m.serverConnect(args);
			m.menu(socket);
			
		} catch(Exception e) {
			System.out.println("예외[MultiClient class]:" +e);
			s.close();
		}
	}
	
	public Socket serverConnect(String[] args) throws IOException {
			String ServerIP = "localhost";
			if (args.length > 0) 
				ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999); //소켓 객체 생성
			System.out.println("서버와 연결이 되었습니다........");
			return socket;
	}
	
	
	public void menu(Socket socket) {
		System.out.println("[메뉴 선택]");
		System.out.println("1.로그인");
		System.out.println("2.회원가입");
		System.out.println("0.종료");
		System.out.print("선택 : ");
		int choice = s.nextInt();
		switch (choice) {
		case 1:
			break;
		case 2:
			join(socket);
			break;
		case 0:
			break;
		default:
			break;
		}
	}
	public void join(Socket socket) {
		Thread sender = new Sender(socket, "");
		System.out.println("사용할 아이디를 입력해주세요 : ");
		String id = s.nextLine();
		System.out.println("사용할 패스워드를 입력해주세요 : ");
		
		System.out.println("패스워드 확인");
		sender.start();
	}

}
