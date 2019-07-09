package chapter06;

import java.util.Scanner;

public class Quiz0606 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("입력할 정수의 갯수를 입력해주세요");
		int max = sc.nextInt();
		double result = 0;
		for (int i = 0; i < max ; i++) {
			int num = sc.nextInt();
			result = result + num;
		}
		result = result / max;
		System.out.println("입력한 값의 평균은" + result + "입니다.");
	}

}
