<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Notification 보낼 내용 입력</h2>

<form action="SendMessageOk.jsp" method="post">
	<input type="text" name="notiTitle" placeholder="알림 타이틀을 입력하세요"/><br>
	<textarea name="notiBody" rows="4" cols="50" placeholder="알림 내용을 입력하세요"></textarea><br>
	<textarea name="message" rows="4" cols="50" placeholder="메세지를 입력하세요"></textarea><br>
	<input type="submit" name="submit" value="Send">
</form>

</body>
</html>