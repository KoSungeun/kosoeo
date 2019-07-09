package chapter06;

import java.util.Scanner;

public class Quiz0612 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("정수 두개를 입력해주세요");
		System.out.println("첫번째");
		int num1 = sc.nextInt();
		System.out.println("두번째");
		int num2 = sc.nextInt();
		int result = 0;
		
		while (true) {
			System.out.print(num1);
			result = result + num1;
			if(num1 > num2) {
				num1--;
				if (num1 < num2) {
					break;
				}

			} else {
				num1++;
				if (num1 > num2) {
					break;
				}
			}
			System.out.print("+");
		} 
		
		System.out.println("=" + result);
		
	}

}
