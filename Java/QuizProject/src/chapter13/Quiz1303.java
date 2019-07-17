package chapter13;

import java.util.Scanner;

public class Quiz1303 {

	public static void main(String[] args) {
		int[] num = new int[10];
		Scanner sc = new Scanner(System.in);
		System.out.println("총 10개의 숫자를 입력하시오.");
		for(int i = 0; i < num.length; i++) {
			num[i] = sc.nextInt();
		}
		String odd = "홀수 : ";
		String even = "짝수 : ";
		for(int i = 0 ; i < num.length; i++) {
			if(num[i] % 2 != 0) {
				if(odd.length() > 5) {
					odd += ", ";
				}
				odd += num[i];
			} else {
				if(even.length() > 5) {
					even += ", ";
				}
				even += num[i];
				
			}	
		}
		System.out.println(odd);
		System.out.println(even);
		
	}

}
