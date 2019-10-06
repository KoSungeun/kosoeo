<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");

	if(id == null) {
	%>
		<jsp:forward page="login.jsp" />
	<%
	} else {
		session.setAttribute("uid", id);
	}
	%>
	<div>
		사용자 아이디 : <%= id %>
	</div>
	<div>
		<input type="text" id="messageinput" />
	</div>
	<div>
		<button type="button" onclick="openSocket()">Open</button>
		<button type="button" onclick="send()">Send</button>
		<button type="button" onclick="closeSocket()">Close</button>
	</div>
	<!-- Server responses get written here -->
	<div id="messages"></div>
	<!-- Script to utilise the WebSocket -->
	<script>
		var webSocket;
		var messages = document.getElementById("messages");
		
		function openSocket() {
			// Ensures only one connection is open at a time
			if(webSocket != undefined && webSocket.readyState != WebSocket.CLOSED) {
				writeResponse("WebSocket is already opened.");
				return;
			}
			
			// Create a new instance of the websocket
			// webSocket = new WebSocket("ws://localhost/ *ProjectName* /echo");
			webSocket = new WebSocket("ws://localhost:8081/WebSocket/websocketendpoint");
			webSocket.onopen = function(event) {
				// For reasons I can't determine, onopen gets called twice
				// and the first time event.data is undefined.
				// Leave a comment if you know the answer.
				if (event.data == undefined)
					return;
				writeResponse(event.data);
			};
			
			webSocket.onmessage = function(event) {
				writeResponse(event.data);
			};
			
			webSocket.onclose = function(event) {
				writeResponse("Connection closed");
			};
		}
		/**
		 *	Send the value of the text input to the server
		 */
		function send() {
			var id = "<%=id%>";
			var text = document.getElementById("messageinput").value;
			webSocket.send(id + "|" + text);
		}
		
		function closeSocket() {
			webSocket.close();
		}
		
		function writeResponse(text) {
			messages.innerHTML += "<br/>" + text;
		}
		
	</script>
	
</body>
</html>