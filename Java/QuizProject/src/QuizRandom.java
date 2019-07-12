import java.util.Random;

public class QuizRandom {

	public static void main(String[] args) {
		Random r = new Random();
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		while(true) {
			num1 = r.nextInt(9)+1;
			num2 = r.nextInt(10);
			num3 = r.nextInt(10);
			if(num1 != num2 && num1 != num3 && num2 != num3) {
				System.out.println(num1 *100 + num2 *10 + num3);
				break;
			}
		}
	}

}
