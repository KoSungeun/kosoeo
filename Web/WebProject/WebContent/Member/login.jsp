<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<jsp:include page="../header.jsp"></jsp:include>

<style>
.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: auto;
}


</style>

<div class="container text-center">
	<form class="form-signin" id="loginForm" action="login.do"
		method="post">
		<h1 class="h3 mb-3 font-weight-normal">로그인</h1>

		<div class="form-group" id="emailGroup">
			<label for="inputEmail" class="sr-only">이메일</label> <input
				type="email" name="email" class="form-control" id="email"
				aria-describedby="emailHelp" placeholder="이메일" autofocus>
			<div></div>
		</div>
		<div class="form-group" id="passwordGroup">
			<label for="inputPassword" class="sr-only">암호</label> <input
				type="password" name="password" class="form-control" id="password"
				placeholder="암호">
			<div></div>
		</div>

		<button class="btn btn-lg btn-primary btn-block" id="loginBtn"
			type="submit">로그인</button>
		<div style="margin-top: 8px">
			<a id="custom-login-btn" href="javascript:loginWithKakao()"> <img
				src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg"
				width="300" />
			</a>
		</div>

		<div id="naverIdLogin" style="margin-top: 8px">
			<a id="naverIdLogin_loginButton" href="#" role="button"><img
				src="https://static.nid.naver.com/oauth/big_g.PNG" width=300></a>
		</div>
		<div id="my-signin2" style="margin-top: 8px"></div>

		<div class="fb-login-button" scope="public_profile, email"
			data-width="300" data-size="large" data-button-type="login_with"
			data-auto-logout-link="false" data-use-continue-as="false"
			onlogin="getINFO()" style="margin-top: 8px"></div>

	</form>
</div>

<script src="form.js"></script>

<script>
	// 구글 로그인
	function onSuccess(googleUser) {
		var profile = googleUser.getBasicProfile();
		loginAjax("google", profile.getEmail(), profile.getName());

	}

	function onFailure(error) {
		console.log(error);
	}

	function renderButton() {
		gapi.signin2.render('my-signin2', {
			'scope' : 'profile email',
			'width' : 300,
			'height' : 50,
			'longtitle' : true,
			'theme' : 'light',
			'onsuccess' : onSuccess,
			'onfailure' : onFailure
		});
	}
	
	// 페이스북	
	function getINFO() {
		FB.api('/me?fields=id,name,email,picture.width(100).height(100).as(picture_small)',
				function(response) {
			if(response.id == null) {
				return;
			}
			loginAjax("facebook", response.email, response.name);
		});
	}


	// 카카오
	function loginWithKakao() {
		Kakao.Auth.login({
			success : function(authObj) {
				signIn(authObj);
			},
			fail : function(err) {
				alert(JSON.stringify(err));
			}
		});
	};

	function signIn(authObj) {
		Kakao.API.request({
			url : '/v2/user/me',
			success : function(res) {
				loginAjax("kakao", res.kakao_account.email,
						res.properties.nickname);
			}
		});
	}

	// 네이버
	window.addEventListener('load', function() {
		naverLogin.getLoginStatus(function(status) {
			if (status) {
				setLoginStatus();
			}
		});
	});
	
	$("#naverIdLogin_loginButton").attr("href", naverLogin.generateAuthorizeUrl());

	function setLoginStatus() {
		var name = naverLogin.user.getName();
		var nickName = naverLogin.user.getNickName();
		var email = naverLogin.user.getEmail();
		
		loginAjax("naver", email, name, nickName)
	}

	function loginAjax(type, email, name, nickName) {
		
		var checkOk = false;
		var queryString;
		if (type == "normal") {
			queryString = "type=normal&" + $('#loginForm').serialize();
		} else {
			queryString = "type=" + type + "&email=" + email + "&name=" + name + "&nickName=" + nickName;
		}
		$.ajax({
			url : 'login.do',
			type : 'post',
			dataType : 'json',
			data : queryString
		}).done(function(data) {
			var result = data["result"];
			if (result == 1) {
				msg = data["msg"];
				checkOk = true;
				var refere = "${header.referer}";
				if(refere.length == 0) {
					location.href = "../main.do";
				} else {
					location.href = refere;
				}
				
			} else if (result == 0) {
				msg = data["msg"];
				toggleVaild("#passwordGroup", msg, checkOk);
			} else if (result == -1) {
				msg = data["msg"];
				toggleVaild("#emailGroup", msg, checkOk);
			}
		});
	}

	$("#loginBtn").click(function() {
		event.preventDefault();
		loginAjax("normal");
	});
</script>
<script src="https://apis.google.com/js/platform.js?onload=renderButton"
	async defer></script>


<jsp:include page="../footer.jsp"></jsp:include>