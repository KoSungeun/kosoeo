<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function joinRequest() {
		$.ajax({
            url: 'JoinProcess',
            type: 'post',
            dataType: 'json',
            data: $('#join-form').serialize(),
            error: function (xhr, status, error) {
                alert(error);
            },
            success: function (data) {
                return_json(data);
            }
        });
		
		 function return_json(data) {
            var result = data["result"];
            console.log(result);
            if(result == "success") {
            	alert("회원가입 성공");
            	window.location.replace("login.jsp");
            } else if(result == "fail") {
            	alert("회원가입 실패");
            }
	     }
	}

</script>
</head>
<body>

	<form id="join-form" method="post">
		아이디 : <input type="text" name="id" size="10"><br>
		비밀번호 : <input type="password" name="pw" size="10"><br>
		이름 : <input type="text" name="name" size="10"><br>
		전화번호 : <select name="phone1">
			<option value="010">010</option>
			<option value="010">016</option>
			<option value="010">017</option>
			<option value="010">018</option>
			<option value="010">019</option>
			<option value="010">011</option>
		</select> -
		<input type="text" name="phone2" size="5"> -
		<input type="text" name="phone3" size="5"> <br>
		성별구분 : <input type="radio" name="gender" value="man">남 &nbsp;
				   <input type="radio" name="gender" value="woman">여 <br>
		<input type="button" value="회원가입" onclick="joinRequest()"> <input type="reset" value="취소">		   
	</form>
</body>
</html>