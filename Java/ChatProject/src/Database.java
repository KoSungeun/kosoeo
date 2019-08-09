import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

public class Database {

	private Connection con = null;
	private PreparedStatement unopstmt = null;
	private PreparedStatement jpstmt = null;
	private PreparedStatement lpstmt = null;
	private PreparedStatement mpstmt = null;
	private PreparedStatement mspstmt = null;
	private String sql = null;
	private ResultSet rs = null;
	public Set<String> loginSet = new HashSet<String>();
	
	
	
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
					if(loginSet.contains(id)) {
						response = "null/로그인 중입니다.";
					} else {
						loginSet.add(id);
						response = String.format("%s/로그인 성공", rs.getString(1));
					}
					
				}
			} else {
				response = "null/ID, PASSWORD가 틀립니다";
			}

		} catch (SQLException sqle) {
			response = "null/알 수 없는 에러가 발생했습니다.";
		}
		return response;
	}
	public Users getUsers(String id, PrintWriter out, BufferedReader in) {
		int uno = 0;
		try {
			sql = "select uno from users where id = ?";
			lpstmt = con.prepareStatement(sql);
			lpstmt.setString(1, id);
			rs = lpstmt.executeQuery();
			if (rs.next()) {
				uno = rs.getInt(1);
			}
		} catch (SQLException sqle) {
				System.out.println("알 수 없는 에러가 발생했습니다.");
		}
		 return new Users(uno, id, out, in);
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
		int fromuno;
		int touno;
		try {
			if(unopstmt == null) {
				sql = "select f.uno, t.uno from users f, users t where f.id = ? and t.id = ?";
				unopstmt = con.prepareStatement(sql);
			}
			unopstmt.setString(1, fromId);
			unopstmt.setString(2, toId);
			rs = unopstmt.executeQuery();
			if(rs.next()) {
				fromuno = rs.getInt(1);
				touno = rs.getInt(2);
			} else {
				return response = "해당 사용자가 없습니다";
			}
			
			
			if(mspstmt == null) {
				sql = "select touno from mute where fromuno = ?";
				mspstmt = con.prepareStatement(sql);
			}
			mspstmt.setInt(1, fromuno);
			rs = mspstmt.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1) == touno) {
					response = "이미차단된 사용자입니다";
					return response;
				} 
			}
			
			
			if(mpstmt == null) {
				sql = "insert into mute (mno, fromuno, touno) values (seq.nextval, ?, ?)";
				mpstmt = con.prepareStatement(sql);
			}
			
			mpstmt.setInt(1, fromuno);
			mpstmt.setInt(2, touno);
			mpstmt.executeUpdate();
		} catch (SQLException sqle) {
			response = "데이터베이스 입력오류입니다.";
			sqle.printStackTrace();
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

