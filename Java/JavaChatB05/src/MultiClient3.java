import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClient3 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("이름을 입력해 주세요.");
		Scanner s = new Scanner(System.in);
		String s_name = s.nextLine();
		
		PrintWriter out = null;
	
		try {
			String ServerIP = "localhost";
			Socket socket = new Socket(ServerIP, 9999); //소켓 객체 생성
			System.out.println("서버와 연결이 되었습니다........");
			
			Thread receiver = new Receiver3(socket);
			receiver.start();
			
			out = new PrintWriter(socket.getOutputStream(), true);
			
			out.println(s_name);
			
			while(out != null) {
				//출력
				String s2 = s.nextLine();
				if(s2.equalsIgnoreCase("q")) {
					out.println(s2);
					break;
				} else {
					out.println(s_name+"=>"+s2);
				}
			}
			
			out.close();
			
			socket.close();
			
		} catch(Exception e) {
			System.out.println("예외[MultiClient class]:" +e);
		}
		s.close();
	}

}
