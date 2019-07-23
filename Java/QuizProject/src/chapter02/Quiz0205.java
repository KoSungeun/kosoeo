package chapter02;

import java.util.Scanner;

public class Quiz0205 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 개의 숫자를 입력하세요.");
		System.out.println("첫 번째 숫자 : ");
		int num1 = sc.nextInt();
		System.out.println("두 번째 숫자 : ");
		int num2 = sc.nextInt();
		int result = num1 * num2;
		result = (result > 0) ? result : result * -1;
		
		System.out.printf("%d * %d의 절대값은 %d 입니다.\n", num1, num2, result);
		sc.close();
	}

}
