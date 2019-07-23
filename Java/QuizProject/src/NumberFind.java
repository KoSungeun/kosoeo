import java.util.Scanner;

public class NumberFind {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String result = sc.next();
		int sum = 0;
		char check;
		for(int i = 0; i < result.length() ; i++) {
			check = result.charAt(i);
			if(check >= '0' && check <= '9') {
				sum += Character.getNumericValue(check);
			}
		}
		System.out.printf("숫자의 합 : %d", sum);
		sc.close();
	}

}
