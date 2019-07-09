package chapter07;

public class Quiz0702 {

	public static void main(String[] args) {
		System.out.println(max(5, 7, 9));
	}
	public static int max(int num1, int num2, int num3) {
		int max = 0;
		if(num1 >= num2 && num1 >= num3) {
			max = num1;
		} else if(num2 >= num1 && num2 >= num3) {
			max = num2;
		} else {
			max = num3;
		}
		return max;
	}

}
