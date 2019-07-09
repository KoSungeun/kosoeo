package chapter06;

import java.util.Scanner;

public class Quiz0603 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("정수를 입력하세요.");
		System.out.println("정수 : ");
		int num1 = sc.nextInt();
		int result = 1;
		while (num1 > 0) {
			System.out.print(num1);
			if(num1 > 1) {
				System.out.print("*");
			}
			result *= num1;
			num1--;
		}
		System.out.print(" = " + result);
	}

}
