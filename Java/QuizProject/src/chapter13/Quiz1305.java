package chapter13;

import java.util.Scanner;

public class Quiz1305 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		System.out.println("단어를 입력하시오. :");
		String input = sc.next();
		
		
//		if (input.equals(sb.append(input).reverse().toString())) {
//			System.out.println("회문입니다.");
//		} else {
//			System.out.println("회문이 아닙니다.");
//		}
		boolean flag = true;
		for(int i = 0; i < input.length() / 2; i++) {
			if(input.charAt(i) != input.charAt(input.length() - 1 - i )) {
				flag = false;
				break;
			}
		}
		if(flag) {
			System.out.println("회문입니다.");
		} else {
			System.out.println("회문이 아닙니다.");
		}
	}

}
