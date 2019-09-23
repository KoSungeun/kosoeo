<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"></jsp:include>

<style>
.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: auto;
}


.form-signin .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

<div class="container text-center">
	<form class="form-signin" id="loginForm" action="login.do" method="post">
		<h1 class="h3 mb-3 font-weight-normal">로그인</h1>
		
		<div class="form-group" id="emailGroup">
    		<label for="inputEmail" class="sr-only">이메일</label>
    		<input type="email" name="email" class="form-control"  id="email" aria-describedby="emailHelp" placeholder="이메일" autofocus>
    		<div>
   			</div>
  		</div>
		<div class="form-group" id="passwordGroup">
 			<label for="inputPassword" class="sr-only">암호</label>
 			<input type="password" name="password" class="form-control" id="password" placeholder="암호">
 			<div>
 		  	</div>
		</div>			
		
		<button class="btn btn-lg btn-primary btn-block" id="loginBtn" type="submit">로그인</button>
		<button class="btn btn-lg btn-primary btn-block" id="loginBtn" onclick=fbLogin(event)>Facebook 로그인</button>
	</form>
	<div class="g-signin2" data-onsuccess="onSignIn"></div>
	
	
</div>



<script src="form.js"></script>
<script>
	
	// 구글 로그인
	function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  loginAjax("google", profile.getEmail(), profile.getName());
	  
	}
	// 페이스북 로그인
	
 	window.fbAsyncInit = function() {
    FB.init({
      appId      : '483967858850686',
      cookie     : true,
      xfbml      : true,
      version    : 'v4.0'
    });

    FB.getLoginStatus(function(response) {
    	console.log(response);
      statusChangeCallback(response);
    });
  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  function statusChangeCallback(response) {
    if (response.status === 'connected') {
      getINFO();
    } else {
      $('#flogin').css('display', 'block');
      $('#flogout').css('display', 'none');
      $('#upic').attr('src', '');
      $('#uname').html('');
    }
  }
	  
  function fbLogin (event) {
	event.preventDefault();
    FB.login(function(response){
      statusChangeCallback(response);
    }, {scope: 'public_profile, email'});
  }

  function fbLogout () {
    FB.logout(function(response) {
      statusChangeCallback(response);
    });
  }
	
	
	
	
	
	function loginAjax(type, email, name) {
		
		var checkOk = false; 
		var queryString; 
		if(type == "normal") {
			queryString = "type=normal&" + $('#loginForm').serialize() ;
		} else {
			queryString = "type=" + type + "&email=" + email + "&name=" + name;	
		}
		alert(queryString);
		$.ajax({
		    url: 'login.do',
		    type: 'post',
		    dataType: 'json',
		    data: queryString
		}).done(function(data) {
			var result =  data["result"];
	        if(result == 1) {
	        	msg = data["msg"];
	        	checkOk = true;
	        	location.href="../main.do"
	        } else if(result == 0) {
	        	msg = data["msg"];
	        	toggleVaild("#passwordGroup", msg, checkOk);
	        } else if(result == -1) {
	        	msg = data["msg"];
	        	toggleVaild("#emailGroup", msg, checkOk);
	        }
		});
	}
	
	
	$("#loginBtn").click(function(){
		event.preventDefault();
		loginAjax("normal") ;
	});

</script>
<jsp:include page="../footer.jsp"></jsp:include>