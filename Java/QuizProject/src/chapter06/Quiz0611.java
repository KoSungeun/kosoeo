package chapter06;

public class Quiz0611 {

	public static void main(String[] args) {
		
		int i = 1;
		int result = 0;
		do {
			if (i % 2 == 0) {
				result = result + i;
			}
			i++;
		} while (i <= 100);
		System.out.println("1~100의 짝수의 합은" + result + "입니다.");
	}

}
