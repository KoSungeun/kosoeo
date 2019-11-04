import java.util.Date;

public class UnderstandDI {

	public static void main(String[] args) {
		// 날짜를 구하기 위해서는 Date 클래스에 의존(사용)해야 한다.
		Date date = new Date();
		System.out.println(date);

	}
	public static void getDate(Date d) {
		Date date = d;
		System.out.println(date);
	}
	
	public static void memberUse1() {
		// 강한 결합 : 직접 생성
		// 생성자가 private로 바뀌면 에러 발생
		Member m1 = new Member();
	}
	public static void memberUse2(Member m) {
		// 약한 결합 : 생성된 것을 받음 - 의존 주입(Dependency Inject)
		// 생성자가 private 으로 바뀌어도영향을 받지 않는다.
		Member m2 = m;
	}

}
// Member의 기능을 사용한다 --> Member의 기능에 의존한다는 의미
class Member {
	String name;
	String nickname;
	public Member() {}
}
