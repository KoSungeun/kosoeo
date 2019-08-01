import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class MyPhoneBook {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	Scanner sc = new Scanner(System.in);
	Connection con = null;
	PreparedStatement pstmt = null;
	String sql = null;
	ResultSet rs = null;

	public static void main(String[] args) {

		MyPhoneBook mpb = new MyPhoneBook();
		mpb.doRun();
	}

	public void doRun() {
		connectDatabase();
		int choice;
		while (true) {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addNumber();
				break;
			case 2:
				selNumber();
				break;
			case 3:
				delNumber();
				break;
			case 4:
				bookMaking();
				break;
			case 5:
				deleteBookmark();
				break;
			case 6:
				showBookmark();
				break;
			case 0:
				closeDatabase();
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘 못 입력하셨습니다.");
				break;
			}

		}
	}

	public void showMenu() {
		System.out.println("[메뉴 선택]");
		System.out.println("1.전화번호 입력");
		System.out.println("2.전화번호 조회");
		System.out.println("3.전화번호 삭제");
		System.out.println("4.북마크 하기");
		System.out.println("5.북마크 삭제하기");
	    System.out.println("6.북마크 보기");
		System.out.println("0.종료");
		System.out.print("선택 : ");
	}

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

	public void selNumber() {
		String name = null;
		while (name == null) {
			System.out.print("조회할 이름 : ");
			name = sc.nextLine();
			if (name.trim().equals("")) {
				System.out.println("다시입력해주세요");
				name = null;
			}
		}
		try {
			sql = "select name, phone_number, email from phone_info where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("이름: " + rs.getString(1));
				System.out.println("전화번호: " + rs.getString(2));
				if(rs.getString(3) != null) {
					System.out.println("이메일: " + rs.getString(3));
				}
			} else {
				System.out.println("해당값이없습니다");
			}

		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}

	}

	public void delNumber() {
		String name = null;
		while (name == null) {
			System.out.print("삭제할 이름 : ");
			name = sc.nextLine();
			if (name.trim().equals("")) {
				System.out.println("다시입력해주세요");
				name = null;
			}
		}

		try {
			sql = "delete phone_info where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			if (pstmt.executeUpdate() > 0) {
				System.out.println("삭제되었습니다.");
			} else {
				System.out.println("해당 값이 없습니다.");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void bookMaking() {
		String name = null;
		while (name == null) {
			System.out.print("북마크 할 이름 : ");
			name = sc.nextLine();
			if (name.trim().equals("")) {
				System.out.println("다시입력해주세요");
				name = null;
			}
		}

		try {
			sql = "update phone_info set bookmark = ? where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, name);
			if (pstmt.executeUpdate() > 0) {
				System.out.println("북마크 되었습니다");
			} else {
				System.out.println("해당 값이 없습니다.");
			}
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
	}
	
	public void deleteBookmark() {
		String name = null;
		while (name == null) {
			System.out.print("삭제할 북마크 할 이름 : ");
			name = sc.nextLine();
			if (name.trim().equals("")) {
				System.out.println("다시입력해주세요");
				name = null;
			}
		}

		try {
			sql = "update phone_info set bookmark = ? where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, name);
			if (pstmt.executeUpdate() > 0) {
				System.out.println("북마크가 삭제 되었습니다");
			} else {
				System.out.println("해당 값이 없습니다.");
			}
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
	
	}
	
	public void showBookmark() {
		System.out.println("북마크");
		try {
			sql = "select name, phone_number, email from phone_info where bookmark = 1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("이름: " + rs.getString(1));
				System.out.println("전화번호: " + rs.getString(2));
				if(rs.getString(3) != null) {
					System.out.println("이메일: " + rs.getString(3));
				}
			}

		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
	}

	public void closeDatabase() {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
