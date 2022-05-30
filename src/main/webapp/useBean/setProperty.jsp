<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaBean의 setProprty</title>
</head>
<body>
	<jsp:useBean id="person" class="dao.Person" scope= "request" />
	
	<!--
	setProperty 는 setter와 같다.
	실제로 setter를 이용하는 것이다.
	name : 객체, property : 필드(속성, 컬럼), value : 값(데이터)
	-->
	<jsp:setProperty name="person" property="id" value="20220601" />
	<jsp:setProperty name="person" property="name" value="지방선거(휴일)" />
	
	<p>아이디 : <% out.println(person.getId()); %></p>
	<p>이름 : <% out.println(person.getName()); %></p>
	
	<br />
	<br />
	<br />
	
	<p>아이디 : <% out.println(person.getId()); %></p>
	<p>이름 : <% out.println(person.getName()); %></p>
</body>
</html>