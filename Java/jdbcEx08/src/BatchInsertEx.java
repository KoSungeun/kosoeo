import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BatchInsertEx {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Properties pro = null;
		FileInputStream fis = null;
		String sql = null;
		boolean commit = false;
		
		
		try {
			pro = new Properties();
			fis = new FileInputStream("D:\\kosoeo\\java\\jdbc.properties");
			pro.load(fis);
			
			String url = pro.getProperty("url");
			String user = pro.getProperty("user");
			String pwd = pro.getProperty("password");
		
			con = DriverManager.getConnection(url, user, pwd);
			
			stmt = con.createStatement();
			sql = "create table test4 ( " +
			      "    id varchar2(10), " +
				  "    password varchar2(10))";
			stmt.executeUpdate(sql);
			
			con.setAutoCommit(false);
			
			stmt.addBatch("insert into test4 " + 
					"values('홍길동', '1111')");
			stmt.addBatch("insert into test4 " + 
					"values('전우치', '2222')");
			stmt.addBatch("insert into test4 " + 
					"values('손오공', '3333')");
			stmt.addBatch("insert into test4 " + 
					"values('이지함', '4444')");
			
			int[] updateCounts = stmt.executeBatch();
			commit = true;
			con.commit();
			
			for(int i : updateCounts) {
				System.out.println(i);
			}
			
			rs = stmt.executeQuery("select * from test4");
			while (rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				System.out.println("id : " + id +", password : " + password);
			}
			
			sql = "drop table test4";
			stmt.executeUpdate(sql);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {	
				if(!commit) con.rollback();
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (SQLException sqle) {}
		}
		
		
		
	}

}
