class 사람 {
	String 이름;
	int 나이;
	int 키;
	
	사람(String 이름, int 나이, int 키) {
		this.이름 = 이름;
		this.나이 = 나이;
		this.키 = 키;
	}
	
	void 인사() {
		System.out.println("안녕하세요");
	}
}



public class MyEarth {

	public static void main(String[] args) {
		// '사람'이란 설계도(클래스)를 이용해 사람(객체) 만들기
		// 만들기 --> 객체를 메모리에 적재
		// 사람 --> 지구에 살게~
		사람 man1 = new 사람("홍길동", 14, 170);
		
		
		man1.이름 = "홍길동";
		man1.나이 = 20;
		man1.키 = 170;
		
		System.out.println(man1.이름);
		man1.인사();
		
	}

}
