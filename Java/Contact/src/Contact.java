import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Contact implements java.io.Serializable {

	private static final long serialVersionUID = -4067696872500457700L;
	private String name;
	private String phoneNumber;
	private String email;
	static List<Contact> list;
	static Scanner sc;
	
	public Contact(String name, String phoneNumber, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	@SuppressWarnings("unchecked")
	public Contact() {
		try(ObjectInputStream oi =
				new ObjectInputStream(new FileInputStream("Object.bin"))) {
			list = (LinkedList<Contact>) oi.readObject();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			list = new LinkedList<Contact>();
		}	
		sc =  new Scanner(System.in);
	}
	
	public void	create() {
		System.out.print("이름:");
		String getName = sc.nextLine();
		System.out.print("전화번호:");
		String getPhoneNumber = sc.nextLine();
		System.out.print("이메일:");
	    String getEmail = sc.nextLine();
		list.add(new Contact(getName, getPhoneNumber, getEmail));
	}
	
	public void delete() {
		System.out.println("삭제할 전화번호나 이름을 입력해주세요");
		String key = sc.next();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).name.equals(key) || list.get(i).phoneNumber.equals(key)) {
				list.remove(i);
			}
		}
	}
	
	public void menu() {
		int select;
		while (true) {
			System.out.println("1.전화번호 입력");
			System.out.println("2.전화번호 조회");
			System.out.println("3.전화번호 삭제");
			System.out.println("4.저장후 종료");
			select = sc.nextInt();
			sc.nextLine();
			switch (select) {
			case 1:
				create();
				break;
			case 2:
				print();
				break;
			case 3:
				delete();
				break;
			case 4:
				save();
				return;
			default:
				break;
			}
		}
		
		
	}

	public void save() {
		try(ObjectOutputStream oo = 
				new ObjectOutputStream(new FileOutputStream("Object.bin"))) {
			oo.writeObject(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void print() {
		System.out.println("이름\t전화번호\t이메일\t");
		for(Contact c : list) {
			System.out.printf("%s\t%s\t\t%s\n", c.name, c.phoneNumber, c.email);
		}
	}
	
	
}
