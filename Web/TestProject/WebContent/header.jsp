<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>

<meta name="google-signin-client_id"
	content="13163274827-kv63tai19hqgak1tj0juug34svp37osa.apps.googleusercontent.com">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="/WebProject/Member/naveridlogin_js_sdk_2.0.0.js"></script>

</body>
<title>WebProject</title>
</head>


<body>

<!-- <div class="alert alert-primary fixed-top" role="alert">
  A simple primary alert—check it out!
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  	</button>
</div> -->
  
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<a class="navbar-brand" href="/WebProject/main.do">WebProject</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link disabled" href="#"
					tabindex="-1" aria-disabled="true">Disabled</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="dropdown01"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">게시판</a>
					<div class="dropdown-menu" aria-labelledby="dropdown01">
						<a class="dropdown-item" href="/WebProject/Board/notice.do">공지사항</a> <a
							class="dropdown-item" href="/WebProject/Board/free.do">자유게시판</a> <a
							class="dropdown-item" href="/WebProject/Board/down.do">자료실</a>
					</div></li>
			</ul>
			<c:choose>

				<c:when test="${MemberDTO == null}">	
					<div class="my-2 my-lg-0">
						<button class="btn btn-secondary mr-sm-2"
							onclick="location.href='/WebProject/Member/loginView.do'">로그인</button>
						<button class="btn btn-secondary my-2 my-sm-0"
							onclick="location.href='/WebProject/Member/joinView.do'">회원가입</button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="my-2 my-lg-0">
						<button class="btn btn-secondary mr-sm-2" onclick=logout()>로그아웃</button>
						<button class="btn btn-secondary my-2 my-sm-0"
							onclick="location.href='/WebProject/Member/modifyView.do'">정보수정</button>
					</div>
				</c:otherwise>

			</c:choose>
			
			<!--  페이스북 초기화  -->
			<div id="fb-root"></div>
			<script async defer crossorigin="anonymous"
					src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v4.0&appId=483967858850686&autoLogAppEvents=1"></script>
			<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
			<script>
			
				// 구글 초기화
				function onLoad() {
					gapi.load('auth2', function() {
						gapi.auth2.init();
					});
				}
				// 카카오 초기화
				Kakao.init('6431066fae7f218669fc25b9b26e2665');
	
				
				// 네이버 초기화
				var naverLogin = new naver.LoginWithNaverId({
	
					clientId : "YlV54vl651q8DjJCOlAq",
					callbackUrl : "http://localhost:8081/WebProject/Member/loginView.do",
					isPopup : false
	
				});

				
				naverLogin.init();
				
				function logout() {
					
					var auth2 = gapi.auth2.getAuthInstance();
					
					FB.logout();
	
					Kakao.Auth.logout();
	
					naverLogin.logout();
					
					auth2.signOut().then(function(){
						location.href = '/WebProject/Member/logout.do';
					});
					

				}
			
			</script>
			
		</div>
	</nav>
	


