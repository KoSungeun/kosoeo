package chapter07;

public class Quiz0703 {
	public static void main(String[] args) {
		System.out.println(min(10, 2, 2));
	}
	public static int min(int num1, int num2, int num3) {
		int min = 0;
		if(num1 <= num2 && num1 <= num3) {
			min = num1;
		} else if(num2 <= num1 && num2 <= num3) {
			min = num2;
		} else {
			min = num3;
		}
		return min;
	}

}
