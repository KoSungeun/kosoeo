import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiServer {

	ServerSocket serverSocket = null;
	Socket socket = null;
	StringUtil su = new StringUtil();
	Database db = new Database();
	RoomTreeMap roomMap;

	public MultiServer() {
		roomMap = new RoomTreeMap();
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
	public void list(Users users, String option) throws IOException {
		users.getOut().println("ID\t방번호\t방이름");
		Map<Integer, Room> roomList = roomMap.getRoomList();

		int usersRno = users.getRno();
		Map<String, Users> usersMap = null;

		if (option.equalsIgnoreCase("a")) { // 전체
			usersMap = roomMap.getAllUsers();
		} else if (option.equalsIgnoreCase("l")) { // 로비
			usersMap = roomList.get(0).getUsersMap();
		} else if (option.equalsIgnoreCase("r")) { // 방
			usersMap = roomList.get(usersRno).getUsersMap();
		}
		for (String id : usersMap.keySet()) {
			users.getOut().println(String.format("%s\t%d\t%s", usersMap.get(id).getId(), usersMap.get(id).getRno(),
					roomList.get(usersMap.get(id).getRno()).getName()));
		}

	}

	public void sendMsg(Users user, String msg) throws IOException {

		List<String> muteList = db.msgMute(user);
		String originMsg = msg; 
		Map<String, Users> usersMap = roomMap.getRoomList().get(user.getRno()).getUsersMap();
		for (String userId : usersMap.keySet()) {
			boolean mute = false;
			for(String muteId : muteList) {
				if(userId.equals(muteId)) {
					mute = true;
					break;
				}
			}
			if(!mute) {
				msg = originMsg;
				for(String word : db.getNgWord(usersMap.get(userId))) {
					msg = msg.replaceAll(word, "***");
				}
				usersMap.get(userId).getOut()
				.println("[" + URLEncoder.encode(user.getId(), "UTF-8") + "]" + URLEncoder.encode(msg, "UTF-8"));	
			}
			if(roomMap.getRoomList().get(user.getRno()).getMonitoringMap().size() > 0) {
				for(Users monitorUser : roomMap.getRoomList().get(user.getRno()).getMonitoringMap().values()) {
					monitorUser.getOut().println(user.getRno() + "번방: [" + URLEncoder.encode(user.getId(), "UTF-8") + "]" + URLEncoder.encode(msg, "UTF-8"));
				}
				
			}
		}

	}

	public void invite(Users users, String inviteId) throws IOException {
		Map<String, Users> allUsers = roomMap.getAllUsers();
		if (roomMap.getRoomList().get(users.getRno()).getMaster() == users.getUno()) {
			if (allUsers.containsKey(inviteId)) {
				users.getOut().println(allUsers.get(inviteId).getId() + "님을 초대 하였습니다.");
				allUsers.get(inviteId).getOut()
						.println("/invite/" + users.getId() + "/" + users.getId() + "님이 초대하였습니다. 수락하시겠습니까? (y/n)");
			} else {
				users.getOut().println("해당 이용자가 없습니다.");
			}
		} else {
			users.getOut().println("방장이 아닙니다");
		}

	}

	public void inviteResponse(Users users, String inviteId, String response) throws IOException {
		Map<String, Users> allUsers = roomMap.getAllUsers();

		if (response.equalsIgnoreCase("y")) {
			roomMap.joinRoom(users, allUsers.get(inviteId).getRno());
		} else if (response.equalsIgnoreCase("n")) {
			allUsers.get(inviteId).getOut().println("거절하였습니다.");
		} else if (response.equalsIgnoreCase("busy")) {
			allUsers.get(inviteId).getOut().println("초대중입니다.");
		} else {
			users.getOut().println("/invite/" + allUsers.get(inviteId).getId() + "/" + allUsers.get(inviteId).getId()
					+ "님이 초대하였습니다. 수락하시겠습니까? (y/n)");
		}

	}

	public void whisper(Users user, String s, int begin, int end) {
		begin = end + 1;
		end = s.indexOf(" ", begin);
		String whisperId = s.substring(begin, end);
		if (roomMap.getAllUsers().containsKey(whisperId)) {
			begin = end + 1;
			String words = s.substring(begin);
			roomMap.getAllUsers().get(user.getId()).getOut().println("To " + whisperId + " : " + words);
			for(String word : db.getNgWord(roomMap.getAllUsers().get(whisperId))) {
				words = words.replaceAll(word, "***");
			}
			roomMap.getAllUsers().get(whisperId).getOut().println("From " + user.getId() + " : " + words);
		} else {
			roomMap.getAllUsers().get(user.getId()).getOut().println("해당 사용자가 없습니다.");
		}
	}

	public void mute(Users user, String muteId) throws IOException {
		user.getOut().println(db.mute(user.getId(), muteId));
	}

	public void createRoom(Users users, BufferedReader in, PrintWriter out) throws IOException {
		String rname = null;
		int limit = 0;
		String selectPass = null;
		String password = null;

		while (rname == null) {
			out.println("방이름을 입력해주세요");
			rname = in.readLine();
		}
		while (limit == 0) {
			out.println("최대 인원수를 입력해주세요");

			try {
				limit = Integer.parseInt(in.readLine());
			} catch (NumberFormatException e) {
				out.println("숫자만 입력해주세요");
			}

		}
		while (selectPass == null) {
			out.println("비밀번호를 설정하시겠습니까?(y/n)");
			selectPass = in.readLine();
			if (selectPass.equalsIgnoreCase("n")) {

			} else if (selectPass.equalsIgnoreCase("y")) {
				while (password == null) {
					out.println("비밀번호를 입력해주세요");
					password = in.readLine();
				}
			} else {
				selectPass = null;
			}
		}
		roomMap.createRoom(users, rname, password, limit);

	}

	public void roomList(Users users, BufferedReader in, PrintWriter out, String option) throws IOException {
		out.println("방번호\t방제목\t현재원\t정원\t공개");
		for (int rno : roomMap.getRoomList().keySet()) {
			if (rno != 0) {
				Room r = roomMap.getRoomList().get(rno);

				String flag;
				if (r.getPassword() != null) {
					flag = "X";
					if (option.equalsIgnoreCase("o")) {
						continue;
					}
				} else {
					flag = "0";
					if (option.equalsIgnoreCase("p")) {
						continue;
					}
				}
				out.println(String.format("%d\t%s\t%d\t%d\t%s", rno, r.getName(), r.getUsersMap().size(), r.getLimit(),
						flag));
			}
		}
	}

	public void kick(Users users, String kickId, String option) {

		String msg;
		if (roomMap.getRoomList().get(users.getRno()).getMaster() == users.getUno()) {
			if (roomMap.getRoomUsers(users.getRno()).containsKey(kickId)) {		
				if (option.equals("b")) {
					msg = "님을 영구강퇴하였습니다.";
					users.getOut().println(kickId);
					roomMap.getRoomList().get(users.getRno()).getBlockMap().put(kickId,
							roomMap.getRoomUsers(users.getRno()).get(kickId));
				} else {
					msg = "님을 강퇴하였습니다.";
				}
				users.getOut().println(kickId + msg);
				roomMap.getRoomUsers(users.getRno()).get(kickId).getOut().println("강퇴당했습니다");
				roomMap.joinRoom(roomMap.getRoomUsers(users.getRno()).get(kickId), 0);
			} else {
				users.getOut().println("해당 이용자가 없습니다.");
			}
		} else {
			users.getOut().println("방장이 아닙니다");
		}
	}
	
	public void turnOver(Users users, String turnOverId) {
		int rno = users.getRno();
		Room room = roomMap.getRoomList().get(rno);
		Users overUser ;
		if(room.getMaster() == users.getUno()) {
			if(room.getUsersMap().containsKey(turnOverId)) {
				overUser = room.getUsersMap().get(turnOverId);
				room.setMaster(overUser.getUno());
				users.getOut().println("방장을 승계하였습니다.");
				overUser.getOut().println("방장이 됐습니다.");
			} else {
				users.getOut().println("해당 이용자가 없습니다.");
			}
		} else {
			users.getOut().println("방장이 아닙니다");
		}
		 
	}
	
	public void roomDestroy(Users users, int rno) {
		if(rno == 0) {
			rno = users.getRno();
		}
		Room room = roomMap.getRoomList().get(rno);
		
		if(room.getMaster() == users.getUno() || users.isAdmin()) {
			Set<String> userIdSet = room.getUsersMap().keySet();
			for(String userId : userIdSet) {
				room.getUsersMap().get(userId).getOut().println("방이 폭파됐습니다.");
				room.getUsersMap().get(userId).setRno(0);
				roomMap.getRoomList().get(0).getUsersMap().put(userId, room.getUsersMap().get(userId));
				room.getUsersMap().get(userId).getOut().println("대기실에 입장하였습니다.");
			}
			roomMap.getRoomList().remove(rno);
		} else {
			users.getOut().println("방장이 아닙니다");
		}
	}
	
	public void ngWord(Users user, String ngWord) {
		user.getOut().println(db.ngWord(user, ngWord));
	}
	
	public void block(Users user, String blockId) throws IOException {
		if(user.isAdmin()) {
			user.getOut().println(db.block(blockId));
			if(roomMap.getAllUsers().containsKey(blockId)) {
				roomMap.getAllUsers().get(blockId).getOut().println("관리자의 의해 블럭됐습니다.");
				roomMap.getAllUsers().get(blockId).getSocket().close();
			}
		} else {
			user.getOut().println("관리자가 아닙니다.");
		}
	}
	
	public void monitoring(Users user, int rno) {
		if(user.isAdmin()) {
			if(roomMap.getRoomList().containsKey(rno)) {
				if(roomMap.getRoomList().get(rno).getMonitoringMap().containsKey(user.getId())) {
					user.getOut().println(rno+"번 방 모니터링을 중지하였습니다.");
					roomMap.getRoomList().get(rno).getMonitoringMap().remove(user.getId());
				} else {
					user.getOut().println(rno+"번 방 모니터링을 시작하였습니다.");
					roomMap.getRoomList().get(rno).getMonitoringMap().put(user.getId(), user);
				}

			} else {
				user.getOut().println(rno+"번 방이 없습니다.");
			}
		} else {
			user.getOut().println("관리자가 아닙니다.");
		}
	}
	
	public void notice (Users user, String msg) {
		if(user.isAdmin()) {
			for(Users sendUser : roomMap.getAllUsers().values()) {
				sendUser.getOut().println("[공지]" + msg);
			}
		} else {
			user.getOut().println("관리자가 아닙니다.");
		}
		
	}

	public static void main(String[] args) {
		// 서버객체 생성
		MultiServer ms = new MultiServer();
		ms.db.connectDatabase();
		ms.init();
	}

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
			Users users = null;
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

						users = db.getUsers(id, socket, out, in);
						roomMap.joinRoom(users);
						out.println(URLEncoder.encode(roomMap.getRoomList().get(users.getRno()).getName() + "에 입장하였습니다 인원수 : "
								+ roomMap.getRoomList().get(users.getRno()).getUsersMap().size(), "UTF-8"));
						while (in != null) {
							s = in.readLine();
							if (s == null) {
								break;
							}
							s = URLDecoder.decode(s, "UTF-8");
							System.out.println(s);

							if (s.startsWith("/")) {
								try {
									String option = null;
									int begin = 1;
									int end = s.indexOf(" ");
									if (end < 0) {
										end = begin;
									}
									String command = s.substring(begin);
									if (command.startsWith("list")) {
										if (end > 0) {
											option = s.substring(s.indexOf("-") + 1);
										}
										list(users, option);
									} else if (s.substring(begin, end).equalsIgnoreCase("to")) {
										whisper(users, s, begin, end);
									} else if (s.substring(begin, end).equalsIgnoreCase("mute")) {
										mute(users, s.substring(end + 1));
									} else if (s.substring(begin).equalsIgnoreCase("croom")) {
										createRoom(users, in, out);
									} else if (s.substring(begin).startsWith("vroom")) {
										if (end > 0) {
											option = s.substring(s.indexOf("-") + 1);
										}
										roomList(users, in, out, option);
									} else if (s.substring(begin).equalsIgnoreCase("jroom")) {
										out.println("입장할 방번호를 입력해주세요");
										String req = in.readLine();
										int rno = Integer.parseInt(req);
										if (roomMap.getRoomList().containsKey(rno)) {
											String rPassword = roomMap.getRoomList().get(rno).getPassword();
											boolean flag = true;
											if (rPassword != null) {
												out.println("비밀번호를 입력해주세요.");
												String password = in.readLine();

												if (!password.equals(rPassword)) {
													out.println("비밀번호가 틀립니다");
													flag = false;
												}
											}
											if (flag) {
												roomMap.joinRoom(users, rno);
											}
										} else {
											out.println("해당 방이 없습니다.");
										}
									} else if (s.substring(begin).equalsIgnoreCase("lroom")) {
										roomMap.joinRoom(users, 0);
									} else if (s.substring(begin).startsWith("invite")) {
										invite(users, s.substring(end + 1));
									} else if (su.requestSplit(s, 1).equals("response")) {
										String userId = su.requestSplit(s, 2);
										response = su.requestSplit(s, 3);
										inviteResponse(users, userId, response);
									} else if (su.requestSplit(s, 1).startsWith("kick")) {
										if (s.indexOf("-") > 0) {
											begin = end + 1;
											end = s.indexOf(" ", begin);
											kick(users, s.substring(begin, end), s.substring(s.indexOf("-") + 1));
										} else {
											kick(users, s.substring(end + 1), "");
										}
									} else if (su.requestSplit(s, 1).startsWith("over")) {
										turnOver(users, s.substring(end + 1));
									} else if (su.requestSplit(s, 1).startsWith("destroy")) {
										if(end > 0) {
											roomDestroy(users, Integer.parseInt(s.substring(end + 1)));
										} else {
											roomDestroy(users, 0);
										}
										
									} else if (su.requestSplit(s, 1).startsWith("ngword")) {
										ngWord(users, s.substring(end + 1));
									} else if (su.requestSplit(s, 1).startsWith("block")) {
										block(users, s.substring(end + 1));
									} else if (su.requestSplit(s, 1).startsWith("monitor")) {
										monitoring(users, Integer.parseInt(s.substring(end + 1)));
									} else if (su.requestSplit(s, 1).startsWith("notice")) {
										notice(users, s.substring(end + 1));
									}
								} catch (Exception e) {
									e.printStackTrace();
									users.getOut().println("잘못된 명령어입니다.");
								}
							} else {
								sendMsg(users, s);
							}

						}

					}
				}
			} catch (IOException e) {
			} finally {
				db.loginSet.remove(id);
				if (users != null) {
					roomMap.leaveRoom(users);
				}

				try {
					in.close();
					out.close();
					socket.close();
					
					
				} catch (IOException e) {
					System.out.println(users.getId() + "님이 접속을 종료하였습니다.");
				}
			}
		}
	}
}
