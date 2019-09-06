<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function loginRequest() {
		$.ajax({
			url : 'LoginProcess',
			type : 'post',
			dataType : 'json',
			data : $('#login-form').serialize(),
			success : function(data) {
				var result = data["result"];
				if (result == "success") {
					alert("로그인 성공");
					window.location.assign("loginResult.jsp");
				} else if (result == "fail") {
					alert("로그인 실패");
				}
			}
		});
	}
</script>
</head>
<body>

	<form id="login-form" action="LoginProcess" method="post">
		아이디 : <input type="text" name="id"><br> 비밀번호 : <input
			type="text" name="pw"><br> <input type="button"
			onclick="loginRequest()" value="로그인">
		<button type="button" onclick="location.href='join.jsp'">회원가입</button>
	</form>

</body>
</html>