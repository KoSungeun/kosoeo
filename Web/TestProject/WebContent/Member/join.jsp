<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"></jsp:include>
<div class=container>
<form action="join.do" method="post" id="joinForm">
  <div class="form-group" id="emailGroup">
    <label for="inputEmail">이메일</label>
    <input type="email" name="email" class="form-control"  id="email" aria-describedby="emailHelp" placeholder="이메일">
    <div>
   	</div>
  </div>
  <div class="form-group" id="passwordGroup">
    <label for="inputPassword">암호</label>
    <input type="password" name="password" class="form-control" id="password" placeholder="패스워드">
    <div>
   	</div>
  </div>
  <div class="form-group" id="passwordCheckGroup">
    <label for="inputPasswordCheck">암호확인</label>
    <input type="password" name="passwordCheck" class="form-control" id="passwordCheck" placeholder="패스워드확인">
    <div>
   	</div>
  </div>
  <div class="form-group" id="nameGroup">
    <label for="inputName">이름</label>
    <input type="text" name="name" class="form-control" id="name" placeholder="이름">
    <div>
   	</div>
  </div>
  <div class="form-group" id="nickNameGroup">
    <label for="inputNickName">별명</label>
    <input type="text" name="nickName" class="form-control" id="nickName" placeholder="별명">
    <div>
   	</div>
  </div>
  <button type="submit" id="submitBtn" class="btn btn-primary">가입</button>
</form>
</div>
<script src="form.js"></script>
<script>

	function emailCheck(callback) {
		var checkOk = false;
		
		var msg = "이메일 형식이 아닙니다";
		var email = $('#email').val().trim();
		var emailCheck = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if(emailCheck.test(email)) {
			$.ajax({
			    url: 'emailCheck.do',
			    type: 'post',
			    dataType: 'json',
			    data: "email=" + email
			}).done(function(data) {
				var result =  data["result"];
		        if(result == "success") {
		        	msg = data["msg"];
		        	checkOk = true;
		        } else if(result == "fail") {
		        	msg = data["msg"];
		        }
		       
		        toggleVaild("#emailGroup", msg, checkOk);
		        callback(checkOk);
			});
		} else {
			toggleVaild("#emailGroup", msg, checkOk);
		}
		callback(checkOk);
	}
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
	
	
	function nameCheck() {
		var checkOk = false;
		var msg = "";
		var name = $('#name').val().trim();
		
		if(name.length <= 0) {
			msg = "이름을 입력해주세요";
		} else {
			msg = "올바른 이름 입니다"
			checkOk = true;
		}
		
		toggleVaild("#nameGroup", msg, checkOk);
		return checkOk;
	}
	
	function nickNameCheck() {
		var checkOk = false;
		var msg = "";
		var name = $('#nickName').val().trim();
		
		if(name.length <= 0) {
			msg = "별명을 입력해주세요";
		} else {
			msg = "올바른 별명 입니다";
			checkOk = true;
		}
		
		toggleVaild("#nickNameGroup", msg, checkOk);
		return checkOk;
	}
	
	$("#submitBtn").click(function(event){
		event.preventDefault();

		emailCheck(function(emailCheck) {
			var checkOk = 0;
			if (emailCheck == false) {
				checkOk++;
			}
			if (passwordCheck() == false) {
				checkOk++;
			} 
			if (passwordConfirm() == false) {
				checkOk++;
			} 
			if (nameCheck() == false) {
				checkOk++;
			}
			if (nickNameCheck() == false) {
				checkOk++;
			}

			if(checkOk == 0) {
				$("#joinForm").submit();	
			}
		});
	});
	
	
	$("#email").focusout(function() {
		emailCheck(function(checkOk){	
		});
	});
	$("#password").focusout(function() {
		passwordCheck();
	});
	$("#passwordCheck").focusout(function() {
		passwordConfirm();
	});
	$("#name").focusout(function() {
		nameCheck();
	});
	$("#nickName").focusout(function() {
		nickNameCheck();
	});
</script>
<jsp:include page="../footer.jsp"></jsp:include>