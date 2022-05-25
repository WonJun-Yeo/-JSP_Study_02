<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="membership.MemberDTO" %>
<%@ page import="membership.MemberDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// form에서 넘겨주는 변수의 값 받기
String userId = request.getParameter("user_id");
String userPwd = request.getParameter("user_pw");

// DAO 객체 호출해서 회원정보에 대한 값을 DTO로 넘겨받는다.
MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.getMemberDTO(userId, userPwd);
dao.close();

// 로그인 성공 여부 확인
if (dto.getId() == null || dto.getId().equals("")) {
	out.println("로그인 실패");
	request.setAttribute("LoginErrMsg", "로그인 오류 입니다.");
	request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
} else {
	out.println("로그인 성공");
	
	session.setAttribute("UserId", dto.getId());
	session.setAttribute("UserName", dto.getName());
	session.setAttribute("UserPw", dto.getPass());
	response.sendRedirect("LoginForm.jsp");
}

%>
</body>
</html>