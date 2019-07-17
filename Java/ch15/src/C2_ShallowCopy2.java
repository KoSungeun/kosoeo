class MyText implements Cloneable {
	private String name;
	
	public MyText(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}

class MyBook implements Cloneable {
	public MyText mt;
	
	public MyBook(String str) {
		this.mt = new MyText(str);
	}
	
	// 정보를 수정함
	public void changeText(String str) {
		mt.setName(str);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	// 정보 출력
	public void showText() {
		System.out.println(mt.getName());
	}
}


public class C2_ShallowCopy2 {

	public static void main(String[] args) {
		MyBook org = new MyBook("홍길동");
		MyBook cpy;
		
		try {
			// 인스턴스 복사
			cpy = (MyBook)org.clone();
			
			// 한 인스턴스의 이름 정보를 수정
			org.changeText("전우치");
			
			// 두 객체의 값이 같이 변경되었음을 확인
			org.showText();
			cpy.showText();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
