<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.model.LoginBean" %>
<%@ include file="mvc.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공 페이지</title>
</head>
<body>
	<p>로그인 성공 했습니다.</p>
	<p><%
	LoginBean bean = (LoginBean) request.getAttribute("bean");
	out.println("아이디  : " + bean.getId());
	%></p>
</body>
</html>