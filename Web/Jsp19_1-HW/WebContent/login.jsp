<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script>

</script>
</head>
<body>

<form action="LoginProcess" method="post">
	아이디 : <input type="text" name="id"><br>
	비밀번호 : <input type="text" name="pw"><br>
	<input type="submit" value="로그인">
	<button type="button" onclick="location.href='join.jsp'">회원가입</button>
</form>

</body>
</html>