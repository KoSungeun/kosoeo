package chapter02;

import java.util.Scanner;

public class Quiz0202 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("한 개의 숫자를 입력하세요.");
		System.out.println("숫자 입력 : ");
		int num = sc.nextInt();
		int result = num * num;
		System.out.printf("%d의 제곱은 %d", num, result);
		sc.close();
	}
}
