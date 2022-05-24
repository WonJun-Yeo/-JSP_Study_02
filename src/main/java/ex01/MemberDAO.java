package ex01;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import common.DBConnPool;

public class MemberDAO extends DBConnPool {
	// DB Connection 객체를 상속해서 쓰면 중복 코드를 방지할 수 있다.
	public MemberDAO() {
		// 부모클래스의 기본 생성자(DBConnPool) 호출
		super();
	}
	
	/* List.jsp 출력을 위한 select 
		1. 레코드 1개를 DTO에 저장
		2. DTO를 List에 저장 (Vector : 멀티스레드 or ArrayList : 싱글스레드)

	 */
	public List<MemberDTO> listMember() {
		
		// List<MemberDTO>
		List<MemberDTO> listMember = new ArrayList<>();
		
		String query = "SELECT * FROM t_member";
		try {
			psmt = con.prepareStatement(query);					// preparedStatement 객체 활성화
			rs = psmt.executeQuery();							// select문을 실행하고 결과 레코드셋을 저장
			
			// rs에 저장된 레코드셋의 각 레코드들을 DTO에 저장 후, List에 add
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				// rs의 값을 변수에 담은 후, DTO에 추가
					// 1. 변수로 할당 
				String id = rs.getString(1);					// 레코드의 첫번째 컬럼(id컬럼)의 값을 가져와 저장
				String pwd = rs.getString("pwd");				// 레코드의 컬럼명 (pwd) 로 값을 가져와 저장
				String name = rs.getString(3);
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
					// 2. DTO의 setter로 값 넣기
				dto.setId(id);
				dto.setPwd(pwd);
				dto.setName(name);
				dto.setEmail(email);
				dto.setJoinDate(joinDate);
				
				// DTO를 List에 저장
				listMember.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Member 테이블에서 데이터 Select 중 오류 발생");
		}
		
		return listMember;
	}
	/* Insert.jsp (DB에 insert)
	 	1. DTO를 매개변수로 받아 그 데이터를 변수에 할당
	 	2. 변수에 할당한 값을 DB에 insert
	 */
	public int addMember (MemberDTO dto) {
		String query = "INSERT INTO t_member (id, pwd, name, email)";		// joinDate컬럼은 default로 오늘날짜가 들어가게 되어 있음
		query = query + " VALUES (?, ?, ?, ?)";
		
		System.out.println("Insert 구문 획인: " + query);
		
		// insert 성공여부 확인 변수
		int result = 0;
		
		// DTO의 데이터들을 getter로 호풀하여 변수에 할당
		String id = dto.getId();
		String pwd = dto.getPwd();
		String name = dto.getName();
		String email = dto.getEmail();
		
		// DB에 insert
		try {
			psmt = con.prepareStatement(query);
			
			// ?에 컬럼값 할당
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			psmt.setString(3, name);
			psmt.setString(4, email);
			
			// psmt 실행
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Insert 진행 중 에러발생");
		}
		
		return result;
	}
	
	/* Delete.jsp (DB 레코드 삭제)
	 	1. 매개변수로 primary key 컬럼의 값을 받아 DB의 비교(여긴 없음)
	 	2. 데이터 삭제
	 */
	
	public int delMember(String id) {						// id 컬럼이 primary key 제약조건을 가짐
		String query = "DELETE t_member";
		query = query + " WHERE id = ?";
		
		int result = 0;
		
		try {
			psmt = con.prepareStatement(query);
			
			// ?에 컬럼값 할당
			psmt.setString(1, id);
			
			// psmt 실행
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DELETE 구문 실행 중 예외 발생");
		}
		
		return result;
	}
	
}
