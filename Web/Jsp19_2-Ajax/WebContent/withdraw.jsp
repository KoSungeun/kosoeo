<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈퇴</title>
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
                	alert("탈퇴 성공");
                	window.location.assign("login.jsp");
                } else if(result == "fail") {
                	alert("탈퇴 실패");
                }
            }
        });
	}
</script>
</head>
<body>
	회원탈퇴를 확인을 위해 비밀번호를 입력해주세요.<br>
	<form action="WithdrawProcess" method="post">
		<input type="password" name="pw">
		<input type="button" value="탈퇴">
	</form>
</body>
</html>