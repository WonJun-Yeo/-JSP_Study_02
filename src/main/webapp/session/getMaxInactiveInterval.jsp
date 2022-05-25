<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>세션 설정 확인</h2>

<h4>Web.xml에 설정된 세견 정보 출력</h4>
세션 유지기간 설정 정보 : <%= session.getMaxInactiveInterval() %> 초 <br>
세션 유지기간 설정 정보 : <%= session.getMaxInactiveInterval() / 60%> 분 <br>
<br>

<h4>세션 설정 정보 변경</h4>
<%
	// 1시간으로 설정
	session.setMaxInactiveInterval(60*60);
	
	// 분
	int time = session.getMaxInactiveInterval()/60;
	
	out.println("세션 유효 시간 : " + time + " 분");
%>

</body>
</html>