class Box8<T> {
	private T ob;
	
	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}


public class B2_PrimitivesAndGeneric {

	public static void main(String[] args) {
		Box8<Integer> iBox = new Box8<Integer>();
		
		iBox.set(125);			// 오토 박싱 진행
		int num = iBox.get();	// 오토 언박싱 진행
		
		System.out.println(num);
	}

}

// 타입 인자로 기본 자료형이 올 수 없다 int (X) : Integer (O)
// 래퍼 클래스가 필요한 이유!
