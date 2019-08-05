import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class Database {

	Scanner sc = new Scanner(System.in);
	Connection con = null;
	PreparedStatement pstmt = null;
	String sql = null;
	ResultSet rs = null;
	
	public void addNumber() {
		String name = null;
		String phoneNumber = null;
		String email = null;

		while (name == null) {
			System.out.print("이름 : ");
			name = sc.nextLine();
			if (name.trim().equals("")) {
				System.out.println("필수입력값입니다");
				name = null;
			}
	
		}

		while (phoneNumber == null) {
			System.out.print("전화번호 : ");
			phoneNumber = sc.nextLine();
			if (phoneNumber.trim().equals("")) {
				System.out.println("필수입력값입니다.");
				phoneNumber = null;
			}
		}
		System.out.print("이메일 : ");
		email = sc.nextLine();

		try {
			sql = "insert into phone_info (name, phone_number, email) values(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNumber);
			if (!email.trim().equals("")) {
				pstmt.setString(3, email);
			} else {
				pstmt.setString(3, null);
			}
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("중복된 이름과 번호가 있습니다.");
		} catch (SQLException sqle) {
			System.out.println("데이터베이스 입력오류입니다.");
		} 

	}
	
	public void connectDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (SQLException sqle) {
			System.out.println("Connection Error");
		}
	}
}
