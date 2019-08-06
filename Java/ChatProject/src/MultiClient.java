import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiClient {

	Scanner s = new Scanner(System.in);
	String ServerIP;

	public MultiClient(String[] args) {
		this.ServerIP = "localhost";
		if (args.length > 0)
			ServerIP = args[0];
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		MultiClient m = new MultiClient(args);
		try {
			m.menu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Socket serverConnect() throws IOException {

		Socket socket = new Socket(ServerIP, 9999); // 소켓 객체 생성
		System.out.println("서버와 연결이 되었습니다........");
		return socket;
	}

	public void menu() throws IOException {

		System.out.println("[메뉴 선택]");
		System.out.println("1.로그인");
		System.out.println("2.회원가입");
		System.out.println("0.종료");
		System.out.print("선택 : ");
		String choice = s.nextLine();
		switch (choice) {
		case "1":
			login();
			break;
		case "2":
			join();
			break;
		case "0":
			return;
		default:
			menu();
			break;
		}

	}

	public void login() throws IOException {
		Socket socket = serverConnect();
		String id = null;
		String password = null;
		while (id == null) {
			System.out.println("ID : ");
			id = s.nextLine();
			if (id.trim().equals("")) {
				System.out.println("필수입력값입니다");
				id = null;
			}

		}
		while (password == null) {
			System.out.println("PASSWORD : ");
			password = s.nextLine();
			if (password.trim().equals("")) {
				System.out.println("필수입력값입니다.");
				password = null;
			}

		}
		String request = String.format("login/%s/%s", id, password);
		Thread sender = new Sender(socket, request);
		Thread receiver = new Receiver(socket);
		sender.start();
		receiver.start();
		
		try {
			receiver.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		ExecutorService exr = Executors.newFixedThreadPool(2);
//		exr.submit(sender);
//		try {
//			exr.submit(receiver).get();
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
		
		menu();
	}

	public void join() throws IOException {
		Socket socket = serverConnect();
		String id = null;
		String password = null;
		String passCheck = null;
		while (id == null) {
			System.out.println("사용할 아이디를 입력해주세요 : ");
			id = s.nextLine();
			if (id.trim().equals("")) {
				System.out.println("필수입력값입니다");
				id = null;
			}

		}
		while (password == null) {
			System.out.println("사용할 패스워드를 입력해주세요 : ");
			password = s.nextLine();
			System.out.println("패스워드 확인");
			passCheck = s.nextLine();

			if (password.equals(passCheck)) {
				if (password.trim().equals("")) {
					System.out.println("필수입력값입니다.");
					password = null;
				}
			} else {
				System.out.println("패스워드가 일치하지 않습니다.");
				password = null;
			}
		}
		String request = String.format("join/%s/%s", id, password);
		Thread sender = new Sender(socket, request);
		Thread receiver = new Receiver(socket);
		sender.start();
		receiver.start();
		try {
			receiver.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu();

	}

}
