package chapter07;

import java.util.Scanner;

public class Quiz0705 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("세개의 정수를 입력해주세요");
		while(true) {
			
			System.out.println("첫번째");
			int num1 = sc.nextInt();
			System.out.println("두번째");
			int num2 = sc.nextInt();
			System.out.println("세번째");
			int num3 = sc.nextInt();
			
			if(num3 == 1) {
				operation1(num1, num2);
				break;
			} else if(num3 == 2) {
				operation2(num1, num2);
				break;
			} else if(num3 == 3) {
				operation3(num1, num2);
				break;
			} else if(num3 == 4) {
				operation4(num1, num2);
				break;
			} else {
				System.out.println("다시 입력해주세요");
			}
			
			
		}
		
	}
	public static void operation1(int num1, int num2) {
		System.out.printf("%d + %d = %d", num1, num2, num1+num2);
	}
	public static void operation2(int num1, int num2) {
		System.out.printf("%d - %d = %d", num1, num2, num1-num2);
	}
	public static void operation3(int num1, int num2) {
		System.out.printf("%d * %d = %d", num1, num2, num1*num2);
	}
	public static void operation4(int num1, int num2) {
		System.out.printf("%d / %d = %d", num1, num2, num1/num2);
	}
	
	

}
