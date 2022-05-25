<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>session 객체의 변수에 할당된 모든 값을 Enumeration(나열)형태로 가져오기</h2>
<%
	String name;			// 세션 객체에 저장된 필드명
	String value;			// 세션 객체에 저장된 필드의 값
	
	// 세션 객체에 감긴 모든 필드를 가져온다. (key:value 형태에서 key에 해당하는 값을 가져온다.)
	Enumeration<String> em = session.getAttributeNames();
	
	int i = 0;
	
	while(em.hasMoreElements()) {
		i++;
		name = em.nextElement().toString();
		value = session.getAttribute(name).toString();
		
		out.println("설정된 세션 속성 이름[" + i + "] : " + name + "<br>");
		out.println("설정된 세션 속성 값[" + i + "] : " + value + "<br><p>");
	}
%>
</body>
</html>