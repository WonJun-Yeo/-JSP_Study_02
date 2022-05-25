<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>session 객체의 모든 속성 삭제</h2>

<h4>세션 정보를 삭제 전</h4>
<%
	String user_id = (String)session.getAttribute("userID");
	String user_pw = (String)session.getAttribute("userPW");
	
	out.println("설정된 세션이름 userID : " + user_id);
	out.println("설정된 세션이름 userPW : " + user_pw);
	
	if(request.isRequestedSessionIdValid() == true) {
		out.println("세션이 유효합니다");
	} else {
		out.println("세션이 유효하지 않습니다.");
	}
%>

<h4>세션 정보를 삭제 후</h4>
<%
	// invalidate() : 모든 속성 삭제
		// key와 value 모두 삭제
	// 로그아웃 시, 모든 세션을 삭제할 때 사용할 수 있다.
	session.invalidate();
	
	// user_id = (String)session.getAttribute("userID");
	// user_pw = (String)session.getAttribute("userPW");
	
	// out.println("설정된 세션이름 userID : " + user_id);
	// out.println("설정된 세션이름 userPW : " + user_pw);
	
	if(request.isRequestedSessionIdValid() == true) {
		out.println("세션이 유효합니다");
	} else {
		out.println("세션이 유효하지 않습니다.");
	}
	
%>
</body>
</html>