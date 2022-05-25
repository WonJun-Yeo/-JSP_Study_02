<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>세션 설정 확인</h2>

<%
	// 날짜 표시 형식
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	// 최초 생성 시간
	long creationTime = session.getCreationTime();
	String creationTimeStr = dateFormat.format(new Date(creationTime));
	
	// 마지막 요청 시간
	long lastTime = session.getLastAccessedTime();
	String lastTimeStr = dateFormat.format(new Date(lastTime));
%>


<ul>
	<li>세션 유지 기간 설정 정보 : <%=session.getMaxInactiveInterval()/60 %> (분)</li>
	<li>세션 아이디 : <%=session.getId() %></li>
	<li>최초 세션 생성 시간 : <%=creationTimeStr %></li>
		<!-- 최초 세션 생성 시간
		 Unix 시간으로 출력
		 Unix 시간 : 1970.01.01 부터 현재 까지의 시간 
		  -->
	<li>마지막 요청 시간 : <%=lastTimeStr %></li>
</ul>


</body>
</html>