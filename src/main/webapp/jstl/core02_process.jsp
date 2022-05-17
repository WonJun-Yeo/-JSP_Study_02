<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String number = request.getParameter("number");
	%>
	
	<!-- 스크립트릿 태크로 출력 -->
	스크립트릿 태그로 출력 : 
	<%= number %> <br/>
	
	<!-- JSTL로 출력 -->
	JSTL로 출력 : 
	<c:out value="${param.number}" /> <br/>
	
	
	<c:choose>
		<c:when test="${param.number % 2 == 0 }">
			<c:out value="${param.number}" /> 은 짝수입니다.	
		</c:when>
		<c:when test="${param.number % 2 == 1 }">
			<c:out value="${param.number}" /> 은 홀수입니다.	
		</c:when>
		<c:otherwise>
			숫자가 아닙니다.
		</c:otherwise>
	</c:choose>	
</body>
</html>