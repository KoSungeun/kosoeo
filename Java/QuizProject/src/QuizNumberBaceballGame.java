import java.util.Random;
import java.util.Scanner;

public class QuizNumberBaceballGame {

	public static void main(String[] args) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int strike;
		int ball;
		String answer;
		String user;
		int count = 0;
		while(true) {
			num1 = r.nextInt(10);
			num2 = r.nextInt(10);
			num3 = r.nextInt(10);
			if(num1 != num2 && num1 != num3 && num2 != num3) {
				answer = sb.append(num1).append(num2).append(num3).toString();
				break;
			}
		}
		System.out.println("숫자로 하는 야구게임시작");
		while(true) {
			
			System.out.printf("세자리 숫자를 입력하세요.(%d회)\n", ++count);
			user = sc.next();
			strike = 0;
			ball = 0;
			System.out.printf("%c:%c:%c\n",user.charAt(0),user.charAt(1),user.charAt(2));
			for(int i = 0; i < 3; i++) {
				int index = answer.indexOf(user.charAt(i));
				if(index > -1) {
					if(i == index){
						strike++;
					} else {
						ball++;
					}
				}
			}
			System.out.printf("%d Strike\t%d Ball\n", strike, ball);
			if(strike == 3) {
				System.out.println("YOU WIN!!!");
				break;
			}
		}
		
	}

}
