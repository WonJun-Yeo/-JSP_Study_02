package com.controller;

import java.io.IOException;
import com.model.LoginBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class myController extends HttpServlet {		// Controller 셋팅

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Client에서 Get 방식으로 요청할 경우 처리하는 블럭
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Client에서  Post 방식으로 요청할 경우 처리하는 블럭
		
		// client의 뷰 페이지로 전송할 contentType을 정의 
		response.setContentType("text.html; charset = utf-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		LoginBean bean = new LoginBean();
		bean.setId(id);
		bean.setPassword(password);
		
		request.setAttribute("bean", bean);
		
		boolean status = bean.validate();		// password가 "admin"이면 true, 아니면 false
		
		if (status) {
			RequestDispatcher rd = request.getRequestDispatcher("mvc_success.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("mvc_error.jsp");
			rd.forward(request, response);
		}
	}
}
