<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"></jsp:include>
<div class=container>
	<form action="withdraw.do" method="post" id="joinForm">
		<div class="form-group" id="passwordGroup">
			<label for="inputPassword">암호</label> <input type="password"
				name="password" class="form-control" id="password"
				placeholder="패스워드">
			<div></div>
		</div>
		<div class="form-group" id="passwordCheckGroup">
			<label for="inputPasswordCheck">암호확인</label> <input type="password"
				name="passwordCheck" class="form-control" id="passwordCheck"
				placeholder="패스워드확인">
			<div></div>
		</div>
		<button type="submit" id="submitBtn" class="btn btn-primary">탈퇴</button>
	</form>
</div>
<script src="form.js"></script>
<script>
	function passwordCheck() {
		
		var checkOk = false;
		var msg = "";
		var password = $('#password').val().trim();
		var passwordCheck = $('#passwordCheck').val().trim();
		
		
		if(password.length < 1) {
			msg = "암호를 입력해주세요";
		} else {
			checkOk = true;
			msg = "올바른 암호 입니다"
		}
		
		if(passwordCheck.length > 0) {
			passwordConfirm();
		}
		toggleVaild("#passwordGroup", msg, checkOk);
		return checkOk;
	}

	function passwordConfirm() {
		var checkOk = false;
		var msg = "";
		
		var password = $('#password').val().trim();
		var passwordCheck = $('#passwordCheck').val().trim();
		
		if(passwordCheck.length < 1) {
			msg = "암호 확인을 입력해주세요";
			toggleVaild("#passwordCheckGroup", msg, checkOk);
		} else {
			if(password != passwordCheck) {
				msg = "입력한 암호가 다릅니다";
			} else {
				msg = "암호확인 완료";
				checkOk = true;
			}
			toggleVaild("#passwordGroup", msg, checkOk);
			toggleVaild("#passwordCheckGroup", msg, checkOk);
		}
		return checkOk;
	}
	
	$("#submitBtn").click(function(event){
		
		event.preventDefault();
		var checkNum = 0;
		var checkOk = false;
		if(passwordCheck() == false){
			checkNum++;
		} else if(passwordConfirm() == false){
			checkNum++;
		}
		
		if(checkNum == 0) {			
			var password = $('#password').val().trim();
			$.ajax({
			    url: 'withdraw.do',
			    type: 'post',
			    dataType: 'json',
			    data: "password=" + password
			}).done(function(data) {
				var result =  data["result"];
		        if(result == "success") {
		        	logout();
		        	location.href="logout.do";
		        	} else if(result == "fail") {
		        	msg = data["msg"];
		        }
		        toggleVaild("#passwordGroup", msg, checkOk);
		        toggleVaild("#passwordCheckGroup", msg, checkOk);
			});
		}
	});
</script>
<jsp:include page="../footer.jsp"></jsp:include>