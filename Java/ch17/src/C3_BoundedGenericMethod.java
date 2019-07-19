class BoxE<T> {
	private T ob;
	
	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}

class BoxFactoryE {
	// 클래스 전체가 아닌 메서드 하나에 대해 제네릭으로 정의
	public static <T extends Number> BoxE<T> makeBox(T o) {
		BoxE<T> box = new BoxE<T>();
		box.set(o);
		
		System.out.println("Boxed data: " + o.intValue());
		return box;
	}
}

class UnboxerE {
	public static <T extends Number> T openBox(BoxE<T> box) {
		System.out.println("Unboxed data: " + box.get().intValue());
		return box.get();
	}
}

public class C3_BoundedGenericMethod {

	public static void main(String[] args) {
		BoxE<Integer> sBox = BoxFactoryE.makeBox(new Integer(5959));
		
		int n = UnboxerE.openBox(sBox);
		System.out.println("Returned data: " + n);
	}

}
