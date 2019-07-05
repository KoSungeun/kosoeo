
public class E_CharTypeUnicode {

	public static void main(String[] args) {
		char ch1 = '헐';
		char ch2 = '확';
		
		char ch3 = 54736;
		char ch4 = 54869;
		
		char ch5 = 0xD5D0;
		char ch6 = 0xD655;
		
		char ch7 = 'A';
		
		System.out.println(ch1 + " " + ch2);
		System.out.println(ch3 + " " + ch4);
		System.out.println(ch5 + " " + ch6);
		System.out.println(ch7 + 32);
		System.out.println(ch7 + " " + (char)(ch7 + 32));
		
	}

}
