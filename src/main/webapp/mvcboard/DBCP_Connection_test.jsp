<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "common.DBConnPool" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <h3> DBCP 커넥션 테스트 </h3>
<% DBConnPool pool = new DBConnPool();    //커넥션 객체 생성 

	pool.close();    //커넥션 객체 반납
%>


</body>
</html>