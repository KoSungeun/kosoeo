package chat.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {
	private StringProperty rno;
	private StringProperty name;
	private StringProperty size;
	private StringProperty pass;
	
	
	public Room(String rno, String name, String size, String pass) {
		this.rno = new SimpleStringProperty(rno);
		this.name = new SimpleStringProperty(name);
		this.size = new SimpleStringProperty(size);
		this.pass = new SimpleStringProperty(pass);
	}


	public String getRno() {
		return rno.get();
	}

	public void setRno(String rno) {
		this.rno.set(rno);;
	}
	
	public StringProperty rnoProperty() {
		return rno;
	}

	public String getName() {
		return name.get();
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	
	public String getSize() {
		return size.get();
	}

	public StringProperty sizeProperty() {
		return size;
	}


	public void setSize(String size) {
		this.size.set(size);
	}


	
	public final String getPass() {
		return pass.get();
	}
	
	public final StringProperty passProperty() {
		return pass;
	}


	public final void setPass(String pass) {
		this.pass.set(pass);
	}



	
	
}
