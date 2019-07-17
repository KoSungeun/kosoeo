package chapter13;

import java.util.Arrays;
import java.util.Scanner;

public class Quiz1308 {

	public static void main(String[] args) {
		int[][] score = new int[4][4];
		Scanner sc = new Scanner(System.in);
		String[] name = {"이순신", "강감찬", "을지문덕", "권율"};
		String[] sub = {"국어", "영어", "수학", "국사"};
		for(int i = 0; i < score.length; i++) {
			System.out.println(name[i] +"의 점수를 입력해주세요.");
			for(int j = 0; j < score[i].length; j++) {
				System.out.println(sub[j]);
				score[i][j] = sc.nextInt();
			}
		}
		System.out.print("구분\t");
		for(String n : name) {
			if(n.equals("을지문덕")) {
				System.out.printf("%s", n);
			} else {
				System.out.printf("%s\t", n);
			}
			
		}
		System.out.println("총점");
		int sum1;
		int sum2[] = new int[4];
		for(int i = 0; i < score.length; i++) {
			System.out.print(sub[i] + "\t");
			sum1 = 0;
			for(int j = 0; j < score[i].length; j++) {
				System.out.printf("%d\t", score[j][i]);
				sum1 += score[j][i];
				sum2[i] += score[i][j];
			}
			System.out.print(sum1);
			System.out.println();
		}
		System.out.print("총점\t");
		int sum3 = 0;
		for(int i = 0; i < sum2.length; i++) {
			System.out.printf("%d\t", sum2[i]);
			sum3 += sum2[i];
		}
		System.out.println(sum3);
	}

}
