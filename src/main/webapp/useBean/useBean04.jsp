<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaBean의 include</title>
</head>
<body>
	<jsp:useBean id="person" class="dao.Person" scope="request" />
	
	<p>아이디 : <%= person.getId() %></p>
	<p>이름 : <%= person.getName() %></p>
	
	<%
	// setter로 값 할당 
	person.setId(20220530);
	person.setName("김유신");
	%>
	
	<br />
	<br />
	<jsp:include page = "useBean03.jsp" />
		
</body>
</html>