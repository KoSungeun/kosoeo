package chapter06;

public class Quiz0604 {

	public static void main(String[] args) {
		int num = 1;
		int result = 0;
		do {
			System.out.print(num);
			if(num < 1000) {
				System.out.print("+");
			} else {
				System.out.print("=");
			}
			result += num;
			num++;
		} while(num <= 1000);
		System.out.print(result);
	}

}
