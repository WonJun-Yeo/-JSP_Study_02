<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
	%>
	<!-- JSTL로 DataBase Connection 설정 -->
	<sql:setDataSource var="dataSource" url="jdbc:oracle:thin:@localhost:1521:XE" driver="oracle.jdbc.driver.OracleDriver" user="HR2" password="1234" />
	
	<sql:update dataSource="${dataSource }" var="resultSet">
		update member set name = ? where id = ? and pass = ?
		<sql:param value="<%= name %>" />
		<sql:param value="<%= id %>" />
		<sql:param value="<%= password %>" />
	</sql:update>
	<c:import var="url" url="sql01.jsp"/>
	
	${url }
	
</body>
</html>