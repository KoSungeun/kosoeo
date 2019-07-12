import java.util.Random;
import java.util.Scanner;

public class QuizHighLowGame {

	public static void main(String[] args) {
		Random r = new Random();
		Scanner sc = new Scanner(System.in);
		int num = 0;
		int answer = 0;
		boolean flag = false;
		boolean exit = false;
		while(true) {
			answer = r.nextInt(101);
			System.out.println("나는 지금 0 부터 100 사이의 값 중에"
					+ " 하나를 생각하겠습니다.");
			System.out.println("당신은 그 숫자를 6회안에 맞추시면 됩니다..");
			
			for(int i = 0; i < 6; i++) {
				System.out.println("몇이라고 생각합니까? <0 to 100> ");
				num = sc.nextInt();
				if(num == answer) {	
					flag = true;
					break;
				} else if (num > answer) {
					System.out.printf("%d 는 제가 정한 숫자 보다 큽니다!\n", num);
				} else if (num < answer) {
					System.out.printf("%d 는 제가 정한 숫자 보다 작습니다!\n", num);
				}
				if(i != 5) {
					System.out.printf("[ %d ]의 기회가 남았습니다.\n", 5 - i);
				}
			}
			
			if(flag) {
				System.out.printf("%d 는 정답입니다. 축하합니다!\n", num);
			} else {
				System.out.println("정답을 맞히지 못했습니다.");
			}
			System.out.println("High / Low 게임을 플레이해 주셔서 감사합니다.");
			
			while(true) {
				System.out.println("다시 하시겠습니까? <y/n>...");
				String sel = sc.next();
				if(sel.equalsIgnoreCase("y")) {
					flag = false;
					break;
				} else if(sel.equalsIgnoreCase("n")) {
					exit = true;
					break;
				} else {
					System.out.println("다시 입력해주세요.");
				}
			}
			
			if(exit) {
				break;
			}
		}
	}

}
