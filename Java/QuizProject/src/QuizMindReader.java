import java.util.Scanner;

public class QuizMindReader {

	public static void main(String[] args) {
		System.out.println("0부터 100 사이의 값 중에 하나를 생각하세요.");
		System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 크면 h를 입력하세요.");
		System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 작으면 l를 입력하세요.");
		System.out.println("제(컴)가 숫자를 맞췄다면 y를 입력해 주세요.");
		Scanner sc = new Scanner(System.in);
		
		int answer;
		String check;
		boolean flag = false;
		int count = 1;
		int max = 101;
		int min = 0;

		while(true) {
			answer = (max + min) / 2;
			System.out.printf("당신이 선택한 숫자는 %d 입니까?\n" , answer);
			while(true) {
				check = sc.next();
				if(check.equalsIgnoreCase("h")){
					min = answer;
					break;
				} else if(check.equalsIgnoreCase("l")) {
					max = answer;
					break;
				} else if(check.equalsIgnoreCase("y")) {
					flag = true;
					break;
				} else {
					System.out.println("다시 입력해주세요");
				}
			}				
			
			if(flag) {
				System.out.printf("정답입니다. [%d회차]", count);
				break;
			}
			count++;
		}
	}

}
