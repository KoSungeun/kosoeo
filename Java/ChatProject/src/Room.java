import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Room {
	static Map<Integer, Room> roomMap = new TreeMap<>();
	private int rno;
	private String name;
	private String password;
	private int limit;
	private int master;
	private Map<String, Users> userMap;

	private Room(Users users, int rno, String name, String password, int limit) {
		
		this.rno = rno;
		this.name = name;
		this.password = password;
		this.limit = limit;
		this.master = users.getUno();
		userMap = new HashMap<String, Users>();
		userMap.put(users.getId(), users);
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getMaster() {
		return master;
	}

	public void setMaster(int master) {
		this.master = master;
	}

	public Map<String, Users> getUsersMap() {
		return userMap;
	}

	static public Map<Integer, Room> getRoomList() {
		return roomMap;
	}

	static public Map<Integer, Room> createRoom(Users users, String name, String password, int limit) {	
		int rno = 0;
		for (int rl : roomMap.keySet()) {
			for (int i = 0; i < 999; i++) {
				if (rl != i) {
					rno = i;
					break;
				}
			}
		}
		roomMap.put(rno, new Room(users, rno, name, password, limit));
		return roomMap;
	}


	static public Map<Integer, Room> joinRoom(Users users, int rno) {
		roomMap.get(rno).getUsersMap().put(users.getId(), users);
		return roomMap;
	}

	static public Map<Integer, Room> joinRoom(Users users) {
		if (roomMap.size() == 0) {
			roomMap.put(0, new Room(users, 0, "대기실", null, 0));
		}
		roomMap.get(0).getUsersMap().put(users.getId(), users);
		return roomMap;
	}
}
