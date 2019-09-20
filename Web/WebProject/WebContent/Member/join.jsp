<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"></jsp:include>
<div class=container>
<form action="join.do" method="post">
  <div class="form-group" id="emailGroup">
    <label for="exampleInputEmail1">이메일</label>
    <input type="email" name="email" class="form-control"  id="email" aria-describedby="emailHelp" placeholder="이메일">
    <div>
   	</div>
  </div>
  <div class="form-group" id="psswordGroup">
    <label for="exampleInputPassword1">패스워드</label>
    <input type="password" name="password" class="form-control" id="password" placeholder="패스워드">
  </div>
  <div class="form-group" id="psswordCheckGroup">
    <label for="exampleInputPassword1">패스워드확인</label>
    <input type="password" name="passwordCheck" class="form-control" id="passwordCheck" placeholder="패스워드확인">
  </div>
  <div class="form-group" id="nameGroup">
    <label for="exampleInputPassword1">이름</label>
    <input type="text" name="name" class="form-control" id="name" placeholder="이름">
  </div>
  <div class="form-group" id="nickNameGroup">
    <label for="exampleInputPassword1">별명</label>
    <input type="text" name="nickName" class="form-control" id="nickName" placeholder="별명">
  </div>
  <button type="submit" class="btn btn-primary">가입</button>
</form>
</div>
<script>
	
	function toggleVaild(selector, msg, checkOk) {

		if(checkOk) {
			$(selector + " > input").addClass("is-valid");
	    	$(selector + " > div").addClass("valid-feedback");
	    	$(selector + " > input").removeClass("is-invalid");
	    	$(selector + " > div").removeClass("invalid-feedback");	
		} else {
			$(selector + " > input").addClass("is-invalid");
	    	$(selector + " > div").addClass("invalid-feedback");
	    	$(selector + " > input").removeClass("is-valid");
	    	$(selector + " > div").removeClass("valid-feedback");
	    		
		}
		$(selector + " > div").text(msg);
	}
	function emailCheck() {

		var checkOk = false;
		var msg = "이메일 형식이 아닙니다";
		var email = $('#email').val().trim();
		var emailCheck = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		if(emailCheck.test(email)) {
			
			$.ajax({
			    url: 'emailCheck.do',
			    type: 'post',
			    dataType: 'json',
			    async: false,
			    data: "email=" + email,
			    success: function (data) {
			    	var result = data["result"];
			        if(result == "success") {
			        	msg = data["msg"];
			        	checkOk = true;
			        } else if(result == "fail") {
			        	msg = data["msg"];
			        }      
			    }
			});
		}
		toggleVaild("#emailGroup", msg, checkOk);
		return checkOk;
	}
	function passwordCheck() {
		
		
	}

	$("#email").focusout(function() {
		emailCheck();
	});
</script>
<jsp:include page="../footer.jsp"></jsp:include>