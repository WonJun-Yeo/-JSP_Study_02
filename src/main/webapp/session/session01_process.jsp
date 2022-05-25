<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	// client form에서 받은 id, passwd
	String user_id = request.getParameter("id");
	String user_pw = request.getParameter("passwd");
	
	// DB에서 가져온 client의 정보와 비교해 일치할 경우, 데이터를 세션에 저장
	if (user_id.equals("admin") && user_pw.equals("1234")) {
		// 세션에 세션 변수의 값을 할당
		session.setAttribute("userID", user_id);	
		session.setAttribute("userPW", user_pw);	
		
		
		out.println("세션 설정이 성공 되었습니다.");
		out.println(user_id + " 님 환영합니다.");
	} else {
		out.println("세션 설정이 실패 했습니다.");
	}
	%>
</body>
</html>