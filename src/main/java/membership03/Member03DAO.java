package membership03;

import common.DBConnPool;

public class Member03DAO extends DBConnPool {
	public Member03DAO() {
		// MemberDAO 객체 생성 시, 부모인 DBConnPool 객체가 생성되면서 DB에 연결
		super();
	}
	
	// Client의 ID와 Password값을 받아서, DB의 member 테이블의 정보와 일치하는지 확인
	// 확인이 된다면 해당 레코드 검색 후, 검색된 값을 DTO에 답아 값을 반환
	public Member03DTO getMemberDTO (String uid, String upass) {
		Member03DTO dto = new Member03DTO();
		
		// 1개의 레코드가 출력되면 id와 pass가 존재하는 것
		// 0개의 레코드가 출력되면 id 와pass 중 하나 이상 DB에 존재하지 않는 것
		String query = "SELECT * FROM member03 WHERE id=? AND pass=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			
			// rs에 레코드가 존재한다는 뜻은 검색되었다는 뜻이다.
			if (rs.next()) {
				// select 결과 레코드를 DTO에 setter로 주입 (* index 번호로 넣어도됨)
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getString("regidate"));
				dto.setGrade(rs.getString("grade"));
				
				System.out.println("인증성공, 값확인");
				System.out.print(dto.getId() + " ");
				System.out.print(dto.getPass() + " ");
				System.out.print(dto.getName() + " ");
				System.out.print(dto.getRegidate() + " ");
				System.out.print(dto.getGrade() + " ");
			}
			
		} catch (Exception e) {
			System.out.println("ID, PASS 확인 과정에서 예외발생");
			e.printStackTrace();
		}
		return dto;
	}
}
