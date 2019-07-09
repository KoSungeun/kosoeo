package chapter06;

import java.util.Scanner;

public class Quiz0609 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("정수를 입력해주세요");
		int num = sc.nextInt();
		for (int i = 9; i > 0; i--) {
			System.out.printf("%d x %d = %d\n", num, i, num * i);
		}
	}

}
