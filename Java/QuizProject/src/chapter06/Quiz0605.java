package chapter06;

import java.util.Scanner;

public class Quiz0605 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("정수를 입력하세요. 0을 입력하면 종료됩니다");
		int result = 0;
		while(true) {
			System.out.println("정수 : ");
			int num = sc.nextInt();
			result = result + num;
			if(num == 0) {
				break;
			} 	
		}
		System.out.println("정수의 합은" + result +"입니다.");
	}

}
