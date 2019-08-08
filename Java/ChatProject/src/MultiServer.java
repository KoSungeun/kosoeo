import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiServer {

	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	StringUtil su = new StringUtil();
	Database db = new Database();

	public MultiServer() {
		// 클라이언트의 출력스트림을 저장할 해쉬맵 생성.
		clientMap = new HashMap<String, PrintWriter>();
		// 해쉬맵 동기화 설정.
		Collections.synchronizedMap(clientMap);
	}

	public void init() {

		try {
			serverSocket = new ServerSocket(9999); // 9999포트로 서버소켓 객체생성.
			System.out.println("서버가 시작되었습니다.");

			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());
				Thread mst = new MultiServerT(socket); // 쓰레드 생성.
				mst.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 접속자 리스트 보내기
	public void list(PrintWriter out) {
		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();
		String msg = "사용자 리스트 [";
		while (it.hasNext()) {
			msg += (String) it.next() + ",";
		}
		msg = msg.substring(0, msg.length() - 1) + "]";
		try {
			out.println(URLEncoder.encode(msg, "UTF-8"));
		} catch (Exception e) {

		}

	}

	// 접속된 모든 클라이언트들에게 메시지를 전달.
	public void sendAllMsg(String user, String msg) {

		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();

		while (it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				if (user.equals(""))
					it_out.println(URLEncoder.encode(msg, "UTF-8"));

				else
					it_out.println("[" + URLEncoder.encode(user, "UTF-8") + "]" + URLEncoder.encode(msg, "UTF-8"));
			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}
	}
	
	public void sendMsg(String id, String msg) {

		for(Room r : Room.getRoomList()) {
			
			Map<String, Users> usersMap = r.getUsersMap();
			if(usersMap.containsKey(id)) {
				for(String mapId : usersMap.keySet()) {
					try {
					usersMap.get(mapId).getOut().println("[" + URLEncoder.encode(id, "UTF-8") + "]" + URLEncoder.encode(msg, "UTF-8"));
					} catch (Exception e) {
						System.out.println("예외:" + e);
					}
				}
			}
			
		}
		

	}
	

	public void whisper(String id, String s, int begin, int end) {
		begin = end + 1;
		end = s.indexOf(" ", begin);
		String whisperId = s.substring(begin, end);
		if (clientMap.containsKey(whisperId)) {
			begin = end + 1;
			String words = s.substring(begin);
			clientMap.get(id).println("To " + whisperId + " : " + words);
			clientMap.get(whisperId).println("From " + id + " : " + words);
		} else {
			clientMap.get(id).println("해당 사용자가 없습니다.");
		}
	}
	
	public void mute(String fromId, String s, int begin, int end) {
		begin = end + 1;
		end = s.indexOf(" ", begin);
		System.out.println(begin);
		String toId = s.substring(begin);
		System.out.println(s);
		clientMap.get(fromId).println(db.mute(fromId, toId));
	}

	public static void main(String[] args) {
		// 서버객체 생성

		MultiServer ms = new MultiServer();
		ms.db.connectDatabase();
		ms.init();
	}

	//////////////////////////////////////////////////
	// 내부 클래스
	// 클라이언트로부터 읽어온 메시지를 다른 클라이언트(socket)에 보내는 역할을 하는 메서드
	class MultiServerT extends Thread {
		Socket socket;
		PrintWriter out = null;
		BufferedReader in = null;

		// 생성자.
		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				out = new PrintWriter(socket.getOutputStream(), true);

			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}

		// 쓰레드를 사용하기 위해서 run()메서드 재정의
		@Override
		public void run() {

			String id = ""; // 클라이언트로부터 받은 이름을 저장할 변수.
			try {
				String request;
				String response;
				String s;

				request = in.readLine();
				request = URLDecoder.decode(request, "UTF-8");
				if (su.requestSplit(request, 0).equals("join")) {
					response = db.join(su.requestSplit(request, 1), su.requestSplit(request, 2));
					out.println(URLEncoder.encode("join/" + response, "UTF-8"));
				} else if (su.requestSplit(request, 0).equals("login")) {
					response = db.login(su.requestSplit(request, 1), su.requestSplit(request, 2));
					out.println(URLEncoder.encode("login/" + response, "UTF-8"));
					if (!su.requestSplit(response, 0).equals("null")) {
						id = in.readLine();
						id = URLDecoder.decode(id, "UTF-8");
						Users users = db.getUsers(id, out);
						Room.joinRoom(users);
						
						while (in != null) {
							s = in.readLine();
							if (s == null) {
								break;
							}
							s = URLDecoder.decode(s, "UTF-8");
							System.out.println(s);

							if (s.startsWith("/")) {
								try {
									int begin = 1;
									int end = s.indexOf(" ");
									if(end < 0) {
										end = begin;
									}
									if (s.substring(begin).equalsIgnoreCase("list"))
										list(out);
									else if (s.substring(begin, end).equalsIgnoreCase("to")) {
										whisper(id, s, begin, end);
									} else if (s.substring(begin, end).equalsIgnoreCase("mute")) {
										mute(id, s, begin, end);
									} else if (s.substring(begin).equalsIgnoreCase("croom")) {
										String rname =null;
										String limit = null;
										String selectPass = null;
										String select =null;
										System.out.println("sasd");
										while(rname == null) {
											out.println("방이름을 입력해주세요");
											rname = in.readLine();
										}
										while(limit == null) {
											out.println("최대 인원수를 입력해주세요");
											limit = in.readLine();
										}
										while(selectPass == null) {
											out.println("비밀번호를 설정하시겠습니까?(y/n)");
											selectPass = in.readLine();
											if(selectPass.equalsIgnoreCase("n")) {
												for(Room r : Room.getRoomList()) {
													if(r.getUsersMap().containsKey(id)) {
														r.getUsersMap().remove(id);
													}
												}
												Room.createRoom(users, rname, null, Integer.parseInt(limit));
												out.println(rname + "방이 생성되었습니다.");
											} else if(selectPass.equalsIgnoreCase("y")) {
												
											}
										}		
										
										
									}
								} catch (StringIndexOutOfBoundsException e) {
									users.getOut().println("잘못된 명령어입니다.");
									e.printStackTrace();
								}
							} else {
								sendMsg(id, s);
							}

						}

					}
				}
			} catch (IOException e) {
				System.out.println("예외:" + e);
			} finally {
				clientMap.remove(id);
				sendAllMsg("", id + "님이 퇴장하셨습니다.");
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");
				try {
					in.close();
					out.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	////////////////////////////////////////////////////////
}
