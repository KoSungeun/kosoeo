package chapter13;

import java.util.Scanner;

public class Quiz1304 {

	public static void main(String[] args) {
		int[] num = new int[10];
		int temp;
		int odd = 0;
		int even = num.length-1;
		Scanner sc = new Scanner(System.in); 
		for(int i = 0; i < num.length; i++) {
			temp = sc.nextInt();
			if(temp % 2 != 0) {
				num[odd] = temp;
				odd++;
			} else {
				num[even] = temp;
				even--;
			}
		}
		for(int print : num) {
			System.out.print(print + " ");
		}
		
	}

}
