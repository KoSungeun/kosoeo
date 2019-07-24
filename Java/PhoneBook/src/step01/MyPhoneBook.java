package step01;

public class MyPhoneBook {

	public static void main(String[] args) {
		PhoneInfo pInfo1 = new PhoneInfo("손오공", "010-7340-1234", "kosoe@gamil.com");
		PhoneInfo pInfo2 = new PhoneInfo("전우치", "010-2340-1234");
		
		pInfo1.showPhoneInfo();
		pInfo2.showPhoneInfo();
	}

}
