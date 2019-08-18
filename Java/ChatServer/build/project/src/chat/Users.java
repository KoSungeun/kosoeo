package chat;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Users {
	
	private int uno;
	private int rno;
	private String id;
	private boolean admin;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public Users(int uno, String id, boolean admin, Socket socket, PrintWriter out, BufferedReader in) {
		this.uno = uno;
		this.rno = 0;
		this.id = id;
		this.admin = admin;
		this.socket = socket; 
		this.out = out;
		this.in = in;
	}

	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
}
