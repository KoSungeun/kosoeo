package step10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class MyPhoneBook {

	Scanner sc = new Scanner(System.in);
	Map<String, PhoneInfo> map = new HashMap<>();

	public static void main(String[] args) {
		MyPhoneBook mpb = new MyPhoneBook();
		mpb.doRun();
	}
	
	public void doRun() {
		readInfo();
		int choice;
		while (true) {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addNumber();
				break;
			case 2:
				selNumber();
				break;
			case 3:
				delNumber();
				break;
			case 4:
				saveInfo();
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘 못 입력하셨습니다.");
				break;
			}

		}
	}
	
	public void showMenu() {
		System.out.println("[메뉴 선택]");
		System.out.println("1.전화번호 입력");
		System.out.println("2.전화번호 조회");
		System.out.println("3.전화번호 삭제");
		System.out.println("4.종료");
		System.out.print("선택 : ");
	}

	public void addNumber() {
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String phoneNumber = sc.nextLine();
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		if(phoneNumber == null) {
			System.out.println("널입니다");
		}
		PhoneInfo pInfo;
		if (email != null) {
			pInfo = new PhoneInfo(name, phoneNumber, email);
		} else {
			pInfo = new PhoneInfo(name, phoneNumber);
		}
		pInfo.showPhoneInfo();
		map.put(name, pInfo);
		System.out.println("맵의 크기 : " + map.size());

	}

	public void selNumber() {
		System.out.print("조회할 이름 : ");
		String name = sc.nextLine();

//		Set<String> ks = map.keySet();
//		for(String s : ks)
//			System.out.println(map.get(s).toString());
//		System.out.println("------------------------------");
		PhoneInfo pInfo;
		if(map.containsKey(name)) {
			 pInfo = map.get(name);
			 pInfo.showPhoneInfo();
		} else {
			System.out.println("해당 값이 없습니다.");
		}
		
	}

	public void delNumber() {
		System.out.print("삭제할 이름 : ");
		String name = sc.nextLine();

		PhoneInfo pInfo = map.remove(name);
		if (pInfo != null) {
			System.out.println("삭제되었습니다.");
//			pInfo.showPhoneInfo();
		} else {
			System.out.println("해당 값이 없습니다.");
		}
//		Set<String> ks = map.keySet();
//		for(String s : ks)
//			System.out.println(map.get(s).toString());
//		System.out.println("------------------------------");
	}



	public void saveInfo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("PhoneInfo.csv"))) {
			Set<String> ks = map.keySet();
			for(String s : ks) {
				bw.write(map.get(s).name + "," + map.get(s).phoneNumber + "," + map.get(s).email);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readInfo() {
		String name;
		String phoneNumber;
		String email = null;
		try(BufferedReader br = new BufferedReader(new FileReader("PhoneInfo.csv"))) {		
			while(true) {
				String token = br.readLine();
				if(token == null) 
					break;
				StringTokenizer st = new StringTokenizer(token, ",");
				name = st.nextToken();
				phoneNumber = st.nextToken();
				if(st.hasMoreTokens()) {
					email = st.nextToken();
				}
				if(email != null) {
					map.put(name, new PhoneInfo(name, phoneNumber, email));
				} else {
					map.put(name, new PhoneInfo(name, phoneNumber));
				}
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
