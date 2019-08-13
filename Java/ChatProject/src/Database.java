import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Database {

	private Connection con = null;
	private PreparedStatement unopstmt = null;
	private PreparedStatement jpstmt = null;
	private PreparedStatement lpstmt = null;
	private PreparedStatement mpstmt = null;
	private PreparedStatement mdpstmt = null;
	private PreparedStatement msgpstmt = null;
	private PreparedStatement ngspstmt = null;
	private PreparedStatement ngdpstmt = null;
	private PreparedStatement ngipstmt = null;
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
				if (rs.getInt("block") == 1) {
					response = String.format("null/차단된사용자입니다", rs.getString(1));
				} else {
					if (loginSet.contains(id)) {
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
		boolean admin = false;
		try {
			sql = "select uno, admin from users where id = ?";
			lpstmt = con.prepareStatement(sql);
			lpstmt.setString(1, id);
			rs = lpstmt.executeQuery();
			if (rs.next()) {
				uno = rs.getInt(1);
				if(rs.getInt(2) == 1) {
					admin = true;
				}
			}
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
		return new Users(uno, id, admin, out, in);
	}

	public List<String> msgMute(Users user) {
		List<String> muteList = new ArrayList<>();
		try {
			sql = "select f.id from mute m, users t, users f " + "where m.fromuno = f.uno " + "and m.touno = t.uno "
					+ "and t.id = ?";
			msgpstmt = con.prepareStatement(sql);
			msgpstmt.setString(1, user.getId());
			rs = msgpstmt.executeQuery();
			while (rs.next()) {
				muteList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return muteList;
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
			if (unopstmt == null) {
				sql = "select f.uno, t.uno from users f, users t where f.id = ? and t.id = ?";
				unopstmt = con.prepareStatement(sql);
			}
			unopstmt.setString(1, fromId);
			unopstmt.setString(2, toId);
			rs = unopstmt.executeQuery();
			if (rs.next()) {
				fromuno = rs.getInt(1);
				touno = rs.getInt(2);
			} else {
				response = "해당 사용자가 없습니다";
				return response;
			}

			if (mdpstmt == null) {
				sql = "delete mute where fromuno = ? and touno = ?";
				mdpstmt = con.prepareStatement(sql);
			}
			mdpstmt.setInt(1, fromuno);
			mdpstmt.setInt(2, touno);
			if (mdpstmt.executeUpdate() > 0) {
				response = "차단을 해제하였습니다.";
				return response;
			}

			if (mpstmt == null) {
				sql = "insert into mute (mno, fromuno, touno) values (mute_seq.nextval, ?, ?)";
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

	public List<String> getNgWord(Users user) {
		List<String> ngList = new ArrayList<>();
		try {
			sql = "select word from ngword where uno = ?";
			ngspstmt = con.prepareStatement(sql);
			ngspstmt.setInt(1, user.getUno());
			rs = ngspstmt.executeQuery();
			while (rs.next()) {
				ngList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ngList;
	}

	public String ngWord(Users user, String ngWord) {
		String response = ngWord + "를 금칙어로 저장했습니다";
		try {
			if (ngdpstmt == null) {
				sql = "delete ngword where uno = ? and word = ?";
				ngdpstmt = con.prepareStatement(sql);
			}
			ngdpstmt.setInt(1, user.getUno());
			ngdpstmt.setString(2, ngWord);
			if (ngdpstmt.executeUpdate() > 0) {
				response = "금칙어를 해제하였습니다.";
				return response;
			}

			if (ngipstmt == null) {
				sql = "insert into ngword (nno, uno, word) values (ng_seq.nextval, ?, ?)";
				ngipstmt = con.prepareStatement(sql);
			}

			ngipstmt.setInt(1, user.getUno());
			ngipstmt.setString(2, ngWord);
			ngipstmt.executeUpdate();
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
