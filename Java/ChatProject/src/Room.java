import java.util.HashMap;
import java.util.Map;

public class Room {
	private int rno;
	private String name;
	private String password;
	private int limit;
	private int master;
	private Map<String, Users> blockMap;
	private Map<String, Users> usersMap;

	Room(int rno, String name, String password, int limit, int master) {
		this.rno = rno;
		this.name = name;
		this.password = password;
		this.limit = limit;
		this.master = master;
		usersMap = new HashMap<String, Users>();
		blockMap = new HashMap<String, Users>();
	}

	public Map<String, Users> getBlockMap() {
		return blockMap;
	}

	public void setBlockMap(Map<String, Users> blockMap) {
		this.blockMap = blockMap;
	}

	public Map<String, Users> getUsersMap() {
		return usersMap;
	}

	public void setUserMap(Map<String, Users> usersMap) {
		this.usersMap = usersMap;
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

}
