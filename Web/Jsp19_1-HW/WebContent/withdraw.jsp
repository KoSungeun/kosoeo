<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈퇴</title>
</head>
<body>
	회원탈퇴를 확인을 위해 비밀번호를 입력해주세요.<br>
	<form action="WithdrawProcess" method="post">
		<input type="password" name="pw">
		<input type="submit" value="탈퇴">
	</form>
</body>
</html>