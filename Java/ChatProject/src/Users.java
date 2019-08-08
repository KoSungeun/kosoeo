import java.io.PrintWriter;


public class Users {
	
	private int uno;
	private int rno;
	private String id;
	private PrintWriter out;
	
	public Users(int uno, String id, PrintWriter out) {
		this.rno = 0;
		this.uno = uno;
		this.id = id;
		this.out = out;
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

	
}
