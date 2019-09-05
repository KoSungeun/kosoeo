<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
	
		int a, b;
		String calc;
		private int calc(int a, int b, String calc) {
			int result;
			if(calc.equals("plus")) {
				return a + b;
			} else if(calc.equals("minus")) {
				return a - b;
			} else if(calc.equals("multiply")) {
				return a * b;
			} else if(calc.equals("division")) {
				return a / b;
			}
			return 0;
		}
	%>
	<%

		try {
			a = Integer.parseInt(request.getParameter("a"));
			b = Integer.parseInt(request.getParameter("b"));
			calc = request.getParameter("calc");
			out.println("계산 결과" + calc(a,b,calc));
		} catch(NumberFormatException e) {
			out.println("값이 없거나 숫자가 아닙니다.");	
		}
	
	%>
	
	<br>
	<a href="calc.html">계산기로 이동</a>

</body>
</html>