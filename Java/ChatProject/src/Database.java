import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class Database {

	Connection con = null;
	PreparedStatement jpstmt = null;
	PreparedStatement lpstmt = null;
	PreparedStatement mpstmt = null;
	PreparedStatement mspstmt = null;
	String sql = null;
	ResultSet rs = null;
	
	
	public String login(String id, String password) {
		String response;
		try {
			sql = "select id, password, block from users where id = ? and password = ?";
			lpstmt = con.prepareStatement(sql);
			lpstmt.setString(1, id);
			lpstmt.setString(2, password);
			rs = lpstmt.executeQuery();
			if (rs.next()) {
				if(rs.getInt("block") == 1) {
					response = String.format("null/차단된사용자입니다", rs.getString(1));
				} else {
					response = String.format("%s/로그인 성공", rs.getString(1));
				}
			} else {
				response = "null/ID, PASSWORD가 틀립니다";
			}

		} catch (SQLException sqle) {
			response = "null/알 수 없는 에러가 발생했습니다.";
		}
		return response;
	}
	
	
	public String join(String id, String password) {
		String response = "회원가입완료";
		try {
			sql = "insert into users (uno, id, password) values (seq.nextval, ?, ?)";
			jpstmt = con.prepareStatement(sql);
			jpstmt.setString(1, id);
			jpstmt.setString(2, password);
			jpstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			response = "중복된 ID가 있습니다.";
		} catch (SQLException sqle) {
			response = "데이터베이스 입력오류입니다.";
		} 
		return response;
	}
	
	public String mute(String fromId, String toId) {
		String response = toId + "님을 차단하였습니다";
		String fromuno = "";
		String touno = "";
		try {
			sql = "select id, fromuno, touno from mute join users" + 
					"on mute.touno = uno" + 
					"where fromuno = (select uno from users where id = ?);";
			mspstmt = con.prepareStatement(sql);
			mspstmt.setString(1, fromId);
			rs = mspstmt.executeQuery();
			while (rs.next()) {
				if(rs.getString(1).equals(toId)) {
					response = "이미차단된 사용자입니다";
					return response;
				} 
			}
			
			sql = "insert into mute (mno, fromuno, touno) values (seq.nextval, ?, ?)";
			mpstmt = con.prepareStatement(sql);
			mpstmt.setString(1, fromuno);
			mpstmt.setString(2, touno);
			mpstmt.executeUpdate();
		} catch (SQLException sqle) {
			response = "데이터베이스 입력오류입니다.";
		} 
		return response;
	}
	
	public void connectDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (SQLException sqle) {
			System.out.println("Connection Error");
		}
	}
}

