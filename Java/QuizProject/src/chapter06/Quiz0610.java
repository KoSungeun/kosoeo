package chapter06;

import java.util.Scanner;

public class Quiz0610 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int result = 0;
		
		System.out.println("5개의 정수를 입력해주세요 (1미만시 재입력)");
		for (int i = 0 ; i < 5;) {
			int num = sc.nextInt();
			if (num < 1) {
				continue;
			}
			result = result + num;
			i++;
		}
		System.out.println("정수의 합은 " + result +"입니다.");
		
	}

}
