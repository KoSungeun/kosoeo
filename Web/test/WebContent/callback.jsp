<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="naveridlogin_js_sdk_2.0.0.js"></script>
<script>
		
		
		
		var naverLogin = new naver.LoginWithNaverId(
			{

				clientId: "YlV54vl651q8DjJCOlAq",
				callbackUrl: "http://localhost:8081/test/callback.jsp",
				isPopup: false,	
				callbackHandle: true
			}
		);
		/* (4) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
		naverLogin.init();
		

		/* (5) 현재 로그인 상태를 확인 */
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					window.location.replace("login.jsp")
				}
			});
		});

	</script>

</body>
</html>