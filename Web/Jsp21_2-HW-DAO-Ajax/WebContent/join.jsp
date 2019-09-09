<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function form_check() {
		if($('#id').val().length == 0) {
			alert("아이디는 필수사항입니다.");
			$('#id').focus();
			return;
		}
		if($('#id').val().length < 4) {
			alert("아이디는 4글자 이상이어야 합니다.");
			$('#id').focus();
			return;
		}
		if($('#pw').val().length == 0) {
			alert("비밀번호는 필수사항입니다.");
			$('#pw').focus();
			return;
		}
		if($('#pw').val() != $('#pw_check').val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$('#pw').focus();
			return;
		}
		if($('#name').val().length == 0) {
			alert("이름은 필수사항입니다.");
			$('#name').val().focus();
			return;
		}
		if($('#eMail').val().length == 0) {
			alert("메일은 필수사항입니다.");
			$('#eMail').focus();
			return;
		}
		submit_ajax();	
	}
	
	function submit_ajax() {
		$.ajax({
		    url: 'JoinProcess',
		    type: 'post',
		    dataType: 'json',
		    data: $('#join-form').serialize(),
		    success: function (data) {
		    	var result = data["result"];
		        alert(data['msg']);
		        if(result == "success") {
		        	window.location.replace("login.jsp");
		        } else if(result == "fail") {
		        	
		        }
		    }
		});
	}
</script>
</head>
<body>

	<form id="join-form">
		아이디 : <input type="text" id="id" name="id" size="20"><br>
		비밀번호 : <input type="password" id="pw" name="pw" size="20"><br>
		비밀번호 확인 : <input type="password" id="pw_check" name="pw_check" size="20"><br>
		이름 : <input type="text" id="name" name="name" size="20"><br>
		메일 : <input type="text" id="eMail" name="eMail" size="20"><br>
		주소 : <input type="text" id="address" name="address" size=50><br><p>
		<input type="button" value="회원가입" onclick="form_check()"> &nbsp;&nbsp;&nbsp;
		<input type="reset" value="로그인" onclick="javascript:window.location='login.jsp'">		   
	</form>
</body>
</html>