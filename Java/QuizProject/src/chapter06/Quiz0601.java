package chapter06;

import java.util.Scanner;

public class Quiz0601 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 개의 숫자를 입력하세요.");
		System.out.println("첫 번째 숫자 : ");
		int num1 = sc.nextInt();
		System.out.println("두 번째 숫자 : ");
		int num2 = sc.nextInt();
		
		int result = num1 - num2;
		
		if(result < 0) {
			result = result * -1;
		}
		System.out.println("두 수의 차는 " + result + "입니다");
		sc.close();
	}

}
