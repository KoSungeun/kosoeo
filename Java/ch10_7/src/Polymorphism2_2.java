// 마이크로소프트사 : 컬러로 인쇄를 할 경우에는 다음의 규격으로 만들어 주세요.
//					  ColorPrintable 을 구현해 주세요.
// 아래아한글 : 구형 프로그램 : print 호출
//				신형 프로그램 : printCMYK 호출
// 삼성, 엡손 : 구형 프린터 : print 구현
//			  : 신형 프린터 : printCMYK 구현
interface ColorPrintable extends Printable {
	void printCMYK(String doc);
}

class PrnColorDrvSamsung implements ColorPrintable {

	public void print(String doc) {
		System.out.println(doc + "\nFrom Samsung(2) : Black-White Ver");
	}

	public void printCMYK(String doc) {
		System.out.println(doc + "\nFrom Samsung(2) : Color Ver");
	}
	
}

class PrnColorDrvEpson implements ColorPrintable {
	public void print(String doc) {
		System.out.println(doc + "\nFrom Epson(2) : Black-White Ver");
	}
	public void printCMYK(String doc) {
		System.out.println(doc + "\nFrom Epson(2) : Color Ver");
	}
	
}

public class Polymorphism2_2 {

	public static void main(String[] args) {
		String doc = "프린터로 출력을 합니다.";
		
		Printable prn1 = new PrnDrvSamsung();
		prn1.print(doc);
		
		ColorPrintable prn2 = new PrnColorDrvSamsung();
		prn2.print(doc);
		prn2.printCMYK(doc);
		
		ColorPrintable prn3 = new PrnColorDrvEpson();
		prn3.print(doc);
		prn3.printCMYK(doc);
		
	}

}
