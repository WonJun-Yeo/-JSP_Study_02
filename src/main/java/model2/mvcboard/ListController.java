package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

public class ListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get 방식으로 요청이 왔을 때 서버에서 처리
		
		// 1. DAO 객체 생성 (Model : 비지니스 로직 처리)
		MVCBoardDAO dao = new MVCBoardDAO();
		
		// 2. 뷰에 전달할 매개변수 저장용 맵 생성 (Key, Value)
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = req.getParameter("searchFeild");
		String searchWord = req.getParameter("searchWord");
		
		if (searchWord != null) {				// 검색어가 존재한다면 map에 값을 저장
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		
		// 3. 게시믈 갯수 알아오기 ( DAO의 selectCount(map) )
		
		int totalCount = dao.selectCount(map);
		
		// System.out.println("전제 레코드 수 : " + totalCount);
		
		// 페이징 처리 부분 start
			// web.xml에서
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
			//System.out.println(pageSize);
			//System.out.println(blockPage);
		
			// 현재 페이지 확인
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if (pageTemp != null && !pageTemp.equals("")) {				// 값이 비어 있지 않을때 넘어온 변수를 정수로 변환하여 저장
			pageNum = Integer.parseInt(pageTemp);
		}
		
			// 목록에 출력할 게시물 범위 계산
		int start = (pageNum - 1) * pageSize + 1;					// 첫 게시물 번호
		int end = pageNum * pageSize;								// 마지막 게시물 번호
		
			// 뷰페이지에 값을 던져줌
		map.put("start", start);
		map.put("end", end);
		
		
		
		// 페이징 처리 부분 end
			// 게시물 목록을 받아오시 (DAO 객체에 작업을 전달)
			// boardLists는 DB의 레코드를 담은 dto 객체를 담고 있다.
		List<MVCBoardDTO> boardLists = dao.selectListPage(map);
		dao.close();
		
		
		// 뷰페이지에 전달할 매개변수들을 추가
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvcboard/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		// 뷰페이지로 데이터 전달, request 영역에 전달할 데이터를 저장 후, List.jsp(뷰페이지)로 forward
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		
		req.getRequestDispatcher("/mvcboard/List.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post 방식으로 요청이 왔을 때 서버에서 처리
		super.doPost(req, resp);
	}
	
}
