class Apple6 {
	public String toString() {
		return "I am an apple.";
	}
}

class Orange6 {
	public String toString() {
		return "I am an orange.";
	}
}

class Box6<T> {
	private T ob;
	
	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}


public class A6_FruitAndBoxFault_Generic {

	public static void main(String[] args) {
		// 과일 담는 박스 생성
		Box6<Apple6> aBox = new Box6<Apple6>();
		Box6<Orange6> oBox = new Box6<Orange6>();
		
		// 과일을 박스에 담은 것일까?
//		aBox.set("Apple");
//		oBox.set("Orange");
		
		// 박스에서 과일을 제대로 꺼낼 수 있을까?
		Apple6 ap = aBox.get();
		Orange6 og = oBox.get();
		
		System.out.println(ap);
		System.out.println(og);
	}

}
