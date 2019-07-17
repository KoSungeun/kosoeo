package chapter13;

import java.util.Scanner;

public class Quiz1301 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] num = new int[5];
		System.out.println("정수 5개를 입력해주세요");
		for(int i = 0; i < num.length; i++) {
			num[i] = sc.nextInt();
		}
		
		System.out.println("최대값은" + max(num));
		System.out.println("최소값은" + min(num));
		System.out.println("모든수의 합은 " + sum(num));
	}
	
	public static int max(int[] num) {
		int max = num[0];
		for(int n : num) {
			if(max < n) {
				max = n;
			}
		}
		return max;
	}
	public static int min(int[] num) {
		int min = num[0];
		for(int n : num) {
			if(min > n) {
				min = n;
			}
			
		}
		return min;
	}
	public static int sum(int[] num) {
		int sum = 0;
		for(int n : num) {
			sum += n;
		}
		return sum;
	}

}
