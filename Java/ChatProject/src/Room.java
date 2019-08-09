import java.util.HashMap;
import java.util.Map;

public class Room {
	private int rno;
	private String name;
	private String password;
	private int limit;
	private int master;
	private Map<String, Users> userMap;

	Room(int rno, String name, String password, int limit, int master) {
		this.rno = rno;
		this.name = name;
		this.password = password;
		this.limit = limit;
		this.master = master;
		userMap = new HashMap<String, Users>();
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
}
