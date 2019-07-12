import java.util.Random;
import java.util.Scanner;

public class QuizRPSGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		int user;
		int com;

		while (true) {
			System.out.println("무엇을 내겠습니까?<1: 가위, 2바위, 3:보> : ");
			user = sc.nextInt();
			com = r.nextInt(3) + 1;
			if (user > 3 || user < 0) {
				System.out.println("다시 입력해주세요");
				continue;
			}
			if (user == 0) {
				System.out.println("*** 게임을 종료 합니다 ***");
				break;
			}

			System.out.printf("사용자 : %s, 컴퓨터 : %s\n", toString(user), toString(com));

			if (user == com) {
				System.out.println("비겼습니다");
			} else if ((user == 1 && com == 3) || (user == 3 && com == 2) || (user == 2 && com == 1)) {
				System.out.println("이겼습니다");
			} else {
				System.out.println("졌습니다");
			}
		}
	}

	public static String toString(int num) {
		String string = "";
		if (num == 1) {
			string = "가위";
		} else if (num == 2) {
			string = "바위";
		} else {
			string = "보";
		}
		return string;
	}

}
