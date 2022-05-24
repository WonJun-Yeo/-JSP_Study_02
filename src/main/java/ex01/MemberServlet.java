package ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ("/member.do")
public class MemberServlet extends HttpServlet {

	// Get 요청을 doHandle 메소드로 보낸다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req,resp);
	}

	// Post 요청을 doHandle 메소드로 보낸다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req,resp);
	}
	
	// Get과 Post method로 넘어오는 요청을 모두 처리
	private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Member Controller 작동확인");
		
		// 한글 처리
		req.setCharacterEncoding("UTF-8");
		
		// 브라우저로 출력할 때, 문서양식 선언
		resp.setContentType("text/html;charset=UTF-8");
		

		// DAO 객체 생성 (요청이 들어왔을 때 DAO 객체의 메소드 호출을 위해서)
		MemberDAO dao = new MemberDAO();
		
		// DTO 객체 생성 (form에서 넘어온 값을 DTO 객체에 넣기위해서)
		MemberDTO dto = new MemberDTO();
		
		// PrintWriter 객체 생성 (출력 페이지 없이(*forward로 view페이지로 넘기는 것이 아닌) 바로 HTML로 출력)
			// client의 웹브라우저에 HTML 출력을 보낸다.
		PrintWriter out = resp.getWriter();
		
		// client의 form에서 넘긴 변수 값(insert, delete 구분하는)을 받는다.
			// command의 값이 addMember일 경우 insert, delMember일 경우 delete
			// form에서 Post 방식으로 hidden으로 넘어오거나
			// Get 방식으로 링크를 통해 넘어올 수도 있다.
		String command = req.getParameter("command");
		
		if (command != null && command.equals("addMember")) {
			// form에서 Post 방식으로 넘어온 값을 변수에 할당
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			
			// DTO setter를 통해 값을 주입
			dto.setId(id);
			dto.setPwd(pwd);
			dto.setName(name);
			dto.setEmail(email);
			
			// DAO의 addMember()메소드에 DTO 객체를 삽입해 insert
			int result = dao.addMember(dto);
			
			// 객체 종료 (DBConnPool을 상속하고 있기때문에 DBConnPool의 close()메소드 사용)
			dao.close();
			
			// insert 성공여부 확인
			if (result == 1) {
				System.out.println("Insert 구문 성공");
				resp.sendRedirect("/MVC_M2/member.do");
			} else if (result == 0) {
				System.out.println("Insert 구문 실패");
			}
			
			
		} else if (command != null && command.equals("delMember")) {
			// form에서 Get 방식으로 넘어온 값을 변수에 할당
			String id = req.getParameter("id");
			
			// DAO의 delMember()메소드에 primary key 컬럼 값을 삽입해 delete
			int result = dao.delMember(id);
			
			// 객체 종료 (DBConnPool을 상속하고 있기때문에 DBConnPool의 close()메소드 사용)
			dao.close();
			
			// delete 성공여부 확인
			if (result == 1) {
				System.out.println("Delete 구문 성공");
				resp.sendRedirect("/MVC_M2/member.do");
			} else if (result == 0) {
				System.out.println("Delete 구문 실패");
			}
		}
		
		// DB의 t_member 테이블의 값을 모두 가져와서 출력
			// listMember()메소드는 select한 결과를 DTO에 담고 각각의 DTO를 listMember 변수에 담는다. 
		List<MemberDTO> listMember = dao.listMember();
		
		// PrintWriter 객체를 이용해 view 페이지 없이 바로 리스트를 출력
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이메일</td><td>이름</td><td>가입일</td><td>삭제</td></tr>");
		
		// 두번째 <tr>에서 listMember의 DTO를 loop돌려 꺼내 getter로 출력
		for (int i = 0; i < listMember.size(); i++) {
			MemberDTO dto2 = new MemberDTO();
			dto2 = (MemberDTO)listMember.get(i);			//  List로 업캐스팅 되어 있어 MemberDTO로 다운 캐스팅
			
			String id = dto2.getId();
			String pwd = dto2.getPwd();
			String name = dto2.getName();
			String email = dto2.getEmail();
			Date joinDate = dto2.getJoinDate();
			
			out.print("<tr><td>" + id + "</td><td>" + pwd + "</td><td>" + email + "</td><td>" + name + "</td><td>" + joinDate + "</td><td><a href='/MVC_M2/member.do?command=delMember&id=" + id + "'>삭제</a></td></tr>");
		}
		
		out.print("</table>");
		out.print("</body></html>");
		out.print("<a href='/MVC_M2/memberForm.jsp'>회원가입</a>");
	}
	
}
