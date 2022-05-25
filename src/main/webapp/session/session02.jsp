<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>session 객체의 변수에 할당된 값 가져오기</h2>
<%
	// Object 타입으로 들어갔기 때문에 타입에 맞게 다운캐스팅 해야 함
		// getAttribute : 세션 객체에 담긴 하나의 변수의 값을 가져올때
	String user_id = (String)session.getAttribute("userID");
	String user_pw = (String)session.getAttribute("userPW");
	
	out.println("<br> 설정된 세션의 속성값 1 : " + user_id);
	out.println("<br> 설정된 세션의 속성값 2 : " + user_pw);
	
	// * 모든 페이지에서 세션의 변수에 들어간 값을 가져왔는데 null일 경우 : 로그인이 안된 상태
%>
</body>
</html>