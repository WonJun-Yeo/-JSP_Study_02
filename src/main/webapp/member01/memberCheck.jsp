<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "logon.LogonDBBean" %>

<% request.setCharacterEncoding("utf-8");%>
<% 
   //사용자의 id값은 세션속성값으로부터 얻어냄
   String id = (String)session.getAttribute("id");
   String passwd = request.getParameter("passwd");

   LogonDBBean manager = LogonDBBean.getInstance();
   int check = manager.userCheck(id, passwd);
   
   out.println(check);
%>