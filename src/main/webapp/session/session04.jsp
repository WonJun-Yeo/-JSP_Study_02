<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>session 객체의 특정 속성 값 삭제</h2>

<h4>세션 정보를 삭제 전</h4>
<%
	String user_id = (String)session.getAttribute("userID");
	String user_pw = (String)session.getAttribute("userPW");
	
	out.println("설정된 세션이름 userID : " + user_id);
	out.println("설정된 세션이름 userPW : " + user_pw);
%>

<h4>세션 정보를 삭제 후</h4>
<%
	// removeAttribute() : 하나의 값 삭제
	// 장바구니에 넣은 값을 session에 저장 후, 특정 값을 삭제할 때 사용할 수 있다.
	session.removeAttribute("userID");
	session.removeAttribute("userPW");
	
	user_id = (String)session.getAttribute("userID");
	user_pw = (String)session.getAttribute("userPW");
	
	out.println("설정된 세션이름 userID : " + user_id);
	out.println("설정된 세션이름 userPW : " + user_pw);
%>
</body>
</html>