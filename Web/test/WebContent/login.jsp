<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <title>Insert title here</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="google-signin-client_id" content="13163274827-kv63tai19hqgak1tj0juug34svp37osa.apps.googleusercontent.com">
    <script src="http://code.jquery.com/jquery.js"></script>
 	<script src="https://apis.google.com/js/platform.js" async defer></script>
 	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
 	<script src="naveridlogin_js_sdk_2.0.0.js"></script>
	

	<script>
	/* 구글 로그인 */
	function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		console.log('ID: ' + profile.getId());
		console.log('Name: ' + profile.getName());
		console.log('Image URL: ' + profile.getImageUrl());
		console.log('Email: ' + profile.getEmail());

		$('#glogin').css('display', 'none');
    	$('#glogout').css('display', 'block');
    	$('#upic').attr('src', profile.getImageUrl());
    	$('#uname').html('[ ' +profile.getName() + ' ]');
	}
	function gsignOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	    	console.log('User signed out.');
	    
	    	$('#glogin').css('display', 'block');
	    	$('#glogout').css('display', 'none');
	    	$('#upic').attr('src', '');
	    	$('#uname').html('');
	    });
	}
	
	/* 페이스북 로그인 */
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
	  
  function fbLogin () {
    FB.login(function(response){
      statusChangeCallback(response);
    }, {scope: 'public_profile, email'});
  }

  function fbLogout () {
    FB.logout(function(response) {
      statusChangeCallback(response);
    });
  }

  function getINFO() {
    FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
      console.log(response);
      $('#flogin').css('display', 'none');
      $('#flogout').css('display', 'block');
      $('#upic').attr('src', response.picture_small.data.url );
      $('#uname').html('[ ' + response.name + ' ]');
    });
  }

</script>
</head>
<body>

	<div id="glogin" class="g-signin2" data-onsuccess="onSignIn"></div>
	
	<div id="glogout" style="display: none;">
		<input type="button" onclick="gsignOut();" value="로그아웃" /><br>	
	</div>


	<div id="flogin" style="display: block;">
  		<input type="button" onclick="fbLogin();" value="로그인" /><br>
	</div>

	<div id="flogout" style="display: none;">
		<input type="button" onclick="fbLogout();" value="로그아웃" /><br>
	</div>

	<div id="klogin" style="display: block">
    <a id="custom-login-btn" href="javascript:loginWithKakao()">
    <img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
    </a>
	</div>

	<div id="klogout" style="display: none;">
    <input type="button" class="btn btn-success" onclick="signOut();" value="로그아웃" /><br>
	</div>
	
	<div id="naverIdLogin" style="display: block">
	<a id="naverIdLogin_loginButton" href="#" role="button"><img src="https://static.nid.naver.com/oauth/big_g.PNG" width=320></a>
	</div>
	<div id="nlogout" style="display: none;">
	<input type="button" class="btn btn-success" onclick="nsignOut();" value="로그아웃" /><br>
	</div>
	
	
	
	<script type='text/javascript'>
    Kakao.init('6431066fae7f218669fc25b9b26e2665');
    function loginWithKakao() {
      // 로그인 창을 띄웁니다.
      Kakao.Auth.login({
        success: function(authObj) {
          //alert(JSON.stringify(authObj));
          signIn(authObj);
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
      });
    };

    function signIn(authObj) {
        //console.log(authObj);
        Kakao.API.request({
            url: '/v2/user/me',
            success: function(res) {
                //console.log(res);
                console.log(res.id);
                $('#klogin').css('display', 'none');
               	$('#klogout').css('display', 'block');
                $('#upic').attr('src', res.properties.thumbnail_image );
               	$('#uname').html('[ ' + res.properties.nickname + ' ]');
             }
         })
	}

    function signOut() {
	    Kakao.Auth.logout(function () {
	    	$('#klogin').css('display', 'block');
	    	$('#klogout').css('display', 'none');
	    	$('#upic').attr('src', '');
	    	$('#uname').html('');
	    });
	}
    
    
	</script>
	
	
	

	<!-- (3) LoginWithNaverId Javscript 설정 정보 및 초기화 -->
	<script>
		
		var naverLogin = new naver.LoginWithNaverId(
			{

				clientId: "YlV54vl651q8DjJCOlAq",
				callbackUrl: "http://localhost:8081/test/callback.jsp",
				isPopup: false,
				loginButton: {color: "green", type: 3, height: 60}
			}
		);
		/* (4) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
		naverLogin.init();
		

		/* (5) 현재 로그인 상태를 확인 */
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					setLoginStatus();
				}
			});
		});

		/* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
		   사용자 정보를 출력합니다. */
		function setLoginStatus() {
			
			var uid = naverLogin.user.getId();
			var profileImage = naverLogin.user.getProfileImage();
			var uName = naverLogin.user.getName();
			var nickName = naverLogin.user.getNickName();
			var eMail = naverLogin.user.getEmail();
			$("#naverIdLogin_loginButton").css("display", "none");
			$("#nlogout").css("display", "block");
			$("#upic").attr('src', profileImage);
			$("#uname").html(uName);
			
			
			/* (7) 로그아웃 버튼을 설정하고 동작을 정의합니다. */
			$("#nlogout").click(function () {
				naverLogin.logout();
				location.reload();
			});
		}
	</script>
	
	<br>
	<img id="upic" src=""><br>
    <span id="uname"></span>


</body>
</html>

