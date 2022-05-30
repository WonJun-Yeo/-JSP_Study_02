<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bean의 getter로 메모리(RAM)의 값을 호출</title>
</head>
<body>
	<jsp:useBean id="person" class="dao.Person" scope="request" />
	
	<p>아이디 : <%= person.getId() %></p>
	<p>이름 : <%= person.getName() %></p>
	
	
	<form action="" method="">
	
	</form>
</body>
</html>