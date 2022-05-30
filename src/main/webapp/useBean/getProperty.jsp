<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaBean의 getProprty</title>
</head>
<body>
	<jsp:useBean id="person" class="dao.Person" scope= "request" />
	
	<!--
	 getProperty 는 getter와 같다.
	 실제로 getter를 이용하는 것이다.
	 name : 객체, property : 필드(속성, 컬럼)
	 -->
	<p>아이디 : <jsp:getProperty name="person" property="id" /></p>
	<p>이름 : <jsp:getProperty name="person" property="name" /></p>
	
	<br />
	<br />
	<br />
	
	<p>아이디 : <%= person.getId() %></p>
	<p>이름 : <%= person.getName() %></p>
	
	<br />
	<br />
	<br />
	
	<p>아이디 : <% out.println(person.getId()); %></p>
	<p>이름 : <% out.println(person.getName()); %></p>
	
	
</body>
</html>