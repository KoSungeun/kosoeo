<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%!
    	Connection con;
    	Statement stmt;
    	ResultSet resultSet;
    	
    	String driver = "oracle.jdbc.driver.OracleDriver";
    	String url = "jdbc:oracle:thin:@localhost:1521:xe";
    	String uid = "scott";
    	String upw = "tiger";
    	
    	
    	String name, id, pw, phone1, phone2, phone3, gender;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function modifyRequast() {
		$.ajax({
            url: 'ModifyProcess',
            type: 'post',
            dataType: 'json',
            data: $('#modifyRequast').serialize(),
            success: function (data) {
            	var result = data["result"];
                if(result == "success") {
                	alert("정보수정 성공");
                	window.location.assign("modifyResult.jsp");
                } else if(result == "fail") {
                	alert("정보수정 실패");
                }
            }
        });
	}
</script>
</head>
<body>
<%
	id = (String)session.getAttribute("id");

	String query = "select * from member where id = '" + id + "'";

	Class.forName(driver);
	con = DriverManager.getConnection(url, uid, upw);
	stmt = con.createStatement();
	resultSet = stmt.executeQuery(query);
	
	String phone = "";
	while (resultSet.next()) {
		name = resultSet.getString("name");
		pw = resultSet.getString("pw");
		phone =resultSet.getString("phone");
  		gender = resultSet.getString("gender");
	}
	
	phone1 = phone.substring(0,3);
	phone2 = phone.substring(4,8);
	phone3 = phone.substring(9,13);
%>

	<form id="modifyRequast" action="ModifyProcess" method="post">
		아이디 :  <%= id %>
		비밀번호 : <input type="password" name="pw" size="10"><br>
		이름 : <input type="text" name="name" size="10" value=<%=name %>><br>
		전화번호 : <select name="phone1">
			<option value="010">010</option>
			<option value="010">016</option>
			<option value="010">017</option>
			<option value="010">018</option>
			<option value="010">019</option>
			<option value="010">011</option>
		</select> -
		<input type="text" name="phone2" size="5" value=<%=phone2 %>> -
		<input type="text" name="phone3" size="5" value=<%=phone3 %>> <br>
		<%
			if(gender.equals("man")) {
		%>
		성별구분 : <input type="radio" name="gender" value="man" checked="checked">남 &nbsp;
				   <input type="radio" name="gender" value="woman">여 <br>
				   
		<%
			} else {
		%>		   
		성별구분 : <input type="radio" name="gender" value="man">남 &nbsp;
				   <input type="radio" name="gender" value="woman" checked="checked">여 <br>
		<%
			}
		%>
				   
		<input type="button" value="정보수정" onclick="modifyRequast()">		   
	</form>
	
</body>
</html>