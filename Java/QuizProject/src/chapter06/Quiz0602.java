package chapter06;

import java.util.Scanner;

public class Quiz0602 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("점수를 입력해주세요");
		System.out.println("국어 점수 : ");
		int num1 = sc.nextInt();
		System.out.println("수학 점수 : ");
		int num2 = sc.nextInt();
		System.out.println("영어 점수 : ");
		int num3 = sc.nextInt();
		
		double average = (num1 + num2 + num3) / 3;
		char result;
		if (average >= 90) {
			result = 'A'; 
		} else if (average >= 80) {
			result = 'B';
		} else if (average >= 70) {
			result = 'C';
		} else if (average >= 50) {
			result = 'D';
		} else {
			result = 'F';
		}
		System.out.printf("학점은 %c 입니다.", result);
	}

}
