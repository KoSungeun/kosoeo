<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./never-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
</head>
<body>

	<table width="700" cellpadding="0" cellspacing="0" border="1" id=>
		<form action="write" method="post">
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="bName" size = "50"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle" size = "50"> </td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> 
				<textarea name="bContent" id="ir1"></textarea> 
				<script type="text/javascript">
					var oEditors = [];
					nhn.husky.EZCreator.createInIFrame({
					    oAppRef: oEditors,
					    elPlaceHolder: "ir1",
					    sSkinURI: "./never-editor/SmartEditor2Skin.html",
					    fCreator: "createSEditor2"
					});
					
				</script>
				</td>
				
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="입력"> &nbsp;&nbsp;
					<a href="list">목록보기</a>
				</td>
			</tr>		
		</form>
	</table>
</body>
</html>