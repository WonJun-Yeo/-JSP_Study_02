<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--
	지정한 클래스의 객체를 id에 선언한 이름으로 객체를 만드는 것과 같다.
	import 후, Calculator bean = new Calculator() 와 동일
	-->
	<jsp:useBean id="bean" class="dao.Calculator" />
	
	<%
	int m = bean.process(5);
	out.println("5의 세제곱은 " + m + " 입니다.");
	%>
	<p><p>
	
	5의 세제곱은 <%= m %> 입니다.
</body>
</html>