<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<jsp:include page="../header.jsp"></jsp:include>

<div class="container-fluid">
	<div id="chatMain" class="mt-3 mb-5" aria-live="polite" aria-atomic="true"
		style="position: relative; min-height: 300px;">
	</div>
</div>


<div class="fixed-bottom">
	<div class="m-1">
		<input type="text" class="form-control" placeholder="입력해주세요"
			id="chatInput">
	</div>
</div>
</div>


<script>
	var webSocket;
	var messages = document.getElementById("messages");

	$('#chatInput').on('keydown', function(e) {
		if (e.which == 13) {
			e.preventDefault();
			send();
			$(this).val("");
		}
	});

	$(document).ready(
			function() {
				
				if (webSocket != undefined
						&& webSocket.readyState != WebSocket.CLOSED) {
					writeResponse("WebSocket is already opened.");
					return;
				}

				webSocket = new WebSocket("ws://localhost:8081/WebProject/endpoint");
				webSocket.onopen = function(event) {
					if (event.data == undefined)
						return;
					writeResponse(event.data);
				};

				webSocket.onmessage = function(event) {
					writeResponse(event.data);
				};

				webSocket.onclose = function(event) {
					var json = {
							head:'알림', 
							msg: '접속이 끊어졌습니다'
					}
					writeResponse(JSON.stringify(json));
				};

			});

	function send() {
		var json = {
				head:'${member.nickName}', 
				msg: document.getElementById("chatInput").value
		}
		
		webSocket.send(JSON.stringify(json));

	}

	function closeSocket() {
		webSocket.close();
	}

	function writeResponse(text) {
		var now = new Date();
		now = now.toLocaleString('ko-KR');
		text = JSON.parse(text);
		$("#chatMain").append(
				$("<div>").attr({
				"role": "alert",
				"aria-live": "assertive",
				"aria-atomic": "true",
				"data-autohide":"false"
			}).addClass("toast").append(
					$("<div>").addClass("toast-header").append(
							$("<i>").addClass("far fa-comment-dots mr-1")
					).append(
						$("<strong>").addClass("mr-auto").text(text.head)		
					).append(
						$("<small>").text(now)		
					).append(
						$("<button>").attr({
						"type": "button",
						"data-dismiss": "toast",
						"aria-label": "Close"
						}).addClass("ml-2 mb-1 close").append(
							$("<span>").attr({
								"aria-hidden":"true"
							}).html("&times;")		
						)
					)
					
				).append(
					$("<div>").addClass("toast-body").text(text.msg)
				)
			);
		
		$('.toast').toast('show');
		$(document).scrollTop($(document).height());
	}
</script>
<jsp:include page="../footer.jsp"></jsp:include>