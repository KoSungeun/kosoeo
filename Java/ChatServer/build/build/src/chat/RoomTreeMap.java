package chat;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RoomTreeMap {
	private Map<Integer, Room> roomMap;
	
	public RoomTreeMap() {
		roomMap = new TreeMap<>();
		Collections.synchronizedMap(roomMap);
	}
	
	public Map<Integer, Room> getRoomList() {
		return roomMap;
	}

	public Map<Integer, Room> createRoom(Users users, String name, String password, int limit) {	
		int rno = 0;
		for(int i = 1; i < 999 ; i++) {
			if(!getRoomList().containsKey(i)) {
				rno = i;
				break;
			}
		}

		
		roomMap.put(rno, new Room(rno, name, password, limit, users.getUno()));
		users.getOut().println(name + "방이 생성되었습니다.");
		joinRoom(users, rno);
		
		return roomMap;
	}

	
	public Map<Integer, Room> leaveRoom(Users users) {
		Map<String, Users> usersMap = roomMap.get(users.getRno()).getUsersMap();
		usersMap.remove(users.getId());
		sendUserList(users);
		int preRno = users.getRno(); 
		users.setRno(0);
		if(preRno != 0 && usersMap.size() == 0) {
			roomMap.remove(preRno);
		} else {
			for(String id : usersMap.keySet()) {
				if(preRno != 0) {
					usersMap.get(id).getOut().println(users.getId() + "님이 퇴실하였습니다.");
				}
			}
		}
		if(preRno != 0) {
			users.getOut().println("퇴실하였습니다.");
		}
		return roomMap;
	}

	public Map<Integer, Room> joinRoom(Users users, int rno) {
		Map<String, Users> usersMap = roomMap.get(rno).getUsersMap();
		
		if(roomMap.get(rno).getBlockMap().containsKey(users.getId())) {
			users.getOut().println("영구강퇴 됐습니다.");
			return roomMap;
		}
		if(usersMap.size() >= roomMap.get(rno).getLimit()) {
			users.getOut().println("정원이 초과하였습니다");
			return roomMap;
		};
		
		leaveRoom(users);
		users.setRno(rno);
		
		for(String id : usersMap.keySet()) {
			if(!users.getId().equals(id)) {
				usersMap.get(id).getOut().println(users.getId() + "님이 입장하였습니다.");
			}
		}
		usersMap.put(users.getId(), users);
		sendUserList(users);
		sendRoomList();
		users.getOut().println(getRoomList().get(rno).getName() + "에 입장하였습니다.");
		return roomMap;
	}

	public Map<Integer, Room> joinRoom(Users users) {
		if(!roomMap.containsKey(0)) {
			roomMap.put(0, new Room(0, "대기실", null, 999, 0));
		}
			
		roomMap.get(0).getUsersMap().put(users.getId(), users);
		sendUserList(users);
		sendRoomList();
		return roomMap;
	}
	
	public void sendUserList(Users user) {
		String userList = "/user/";
		for (Users u : getRoomList().get(user.getRno()).getUsersMap().values()) {
			String Id = u.getId();
			if(getRoomList().get(u.getRno()).getMaster() == u.getUno()) {
				Id += "[방장]";
			}
			userList += Id + "/";
		}
		for (Users u : getRoomList().get(user.getRno()).getUsersMap().values()) {
			u.getOut().println(userList);
		}
	}
	
	public void sendRoomList() {
		String roomList = "/room/";
		for(Room r : getRoomList().values()) {
			
			String size = r.getLimit() +"/" + r.getUsersMap().size();
			String pass = "O";
			if(r.getPassword() != null) {
				pass = "X";
			}
			roomList += r.getRno()+"/"+r.getName()+"/"+size+ "/"+pass+"@";
		}
		for(Users allUser : getAllUsers().values()) {
			allUser.getOut().println(roomList);
		}
	}
	
	public Map<String, Users> getAllUsers() {
		return roomMap.values().stream().flatMap(e -> e.getUsersMap().entrySet().stream())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	public Map<String, Users> getRoomUsers(int rno) {
		return getRoomList().get(rno).getUsersMap();
	}
}
