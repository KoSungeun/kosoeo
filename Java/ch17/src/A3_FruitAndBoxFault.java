class Apple3 {
	public String toString() {
		return "I am an apple.";
	}
}

class Orange3 {
	public String toString() {
		return "I am an orange.";
	}
}

class Box3 {
	private Object ob;
	
	public void set(Object o) {
		ob = o;
	}
	public Object get() {
		return ob;
	}
}


public class A3_FruitAndBoxFault {

	public static void main(String[] args) {
		// 과일 담는 박스 생성
		Box3 aBox = new Box3();
		Box3 oBox = new Box3();
		
		// 과일을 박스에 담은 것일까?
		aBox.set("Apple");
		oBox.set("Orange");
		
		// 박스에서 과일을 제대로 꺼낼 수 있을까?
		Apple3 ap = (Apple3)aBox.get();
		Orange3 og = (Orange3)oBox.get();
		
		System.out.println(ap);
		System.out.println(og);
	}

}
