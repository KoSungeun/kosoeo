<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP/Servlet 21-1</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function submit_ajax() {
		$.ajax({
		    url: 'LoginProcess',
		    type: 'post',
		    dataType: 'json',
		    data: $('#login-form').serialize(),
		    success: function (data) {
		    	var result = data["result"];      
		        if(result == "success") {
		        	window.location.replace("main.jsp");
		        } else if(result == "fail") {
		        	alert(data['msg']);
		        }
		    }
		});
	}
</script>
</head>
<body>
	<form id="login-form" method="post">
		아이디 : <input type="text" name="id"
						value="<% if(session.getAttribute("id") != null)
									 out.println(session.getAttribute("id"));										
							%>"> <br>
		비밀번호 : <input type="password" name="pw"><br><p>
		<input type="button" value="로그인" onclick="submit_ajax()"> &nbsp;&nbsp;      
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'"> &nbsp;&nbsp;&nbsp;
	</form>

</body>
</html>