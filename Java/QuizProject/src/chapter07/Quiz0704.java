package chapter07;

public class Quiz0704 {

	public static void main(String[] args) {
		System.out.println(fahrenheitConversion(32));
		System.out.println(celsiusConversion(89.6));
	}
	public static double fahrenheitConversion(double celsius) {
		return 1.8 * celsius + 32; 
	}
	public static double celsiusConversion(double fahrenheit) {
		return (fahrenheit - 32) / 1.8;
	}

}
