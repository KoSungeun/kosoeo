<%@page import="com.study.jsp.MemberDto"%>
<%@page import="com.study.jsp.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String id = (String)session.getAttribute("id");
	MemberDao dao = MemberDao.getInstance();
	MemberDto dto = dao.getMember(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function form_check() {
		if($("#pw").val() == "") {
			alert("패스워드를 입력하세요.");
			$("#pw").focus();
			return;
		}
		
		if($("#pw").val() != $("#pw_check").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#pw").focus();
			return;
		}
		
		if($("#eMail").val() == 0) {
			alert("메일은 필수사항입니다.");
			$("#eMail").focus();
			return;
		}
		submit_ajax();
	}
	
	function submit_ajax() {
		$.ajax({
		    url: 'ModifyProcess',
		    type: 'post',
		    dataType: 'json',
		    data: $('#modify-form').serialize(),
		    success: function (data) {
		    	var result = data["result"];
		        alert(data['msg']);
		        if(result == "success") {
		        	window.location.replace("main.jsp");
		        } else if(result == "fail") {
		        	
		        }
		    }
		});
	}
</script>
</head>
<body>
	<form id="modify-form">
		아이디 : <%=dto.getId() %><br>
		비밀번호 : <input type="password" id="pw" name="pw" size="20"><br>
		비밀번호 확인 : <input type="password" id="pw_check" name="pw_check" size="20"><br>
		이름 : <%= dto.getName() %>
		메일 : <input type="text" id="eMail" name="eMail" size="20" value="<%= dto.geteMail() %>"><br>
		주소 : <input type="text" id="address" name="address" size=50 value="<%=dto.getAddress() %>"><br><p>
		<input type="button" value="수정" onclick="form_check()"> &nbsp;&nbsp;&nbsp;
		<input type="reset" value="취소" onclick="javascript:window.location='main.jsp'">
	</form>
</body>
</html>