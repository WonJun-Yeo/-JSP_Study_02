package logon;

import common.DBConnPool;
import work.crypt.BCrypt;
import work.crypt.SHA256;

// DAO : 실제 DB에 Select, Insert, Delete, Update를 처리
public class LogonDBBean extends DBConnPool{
	// LogonDB 전역객체 생성 : 한 개의 객체만 생성해서 공유 (싱글톤 패턴)
		// 외부의 다른 클래스에서 new 키워드를 사용해 여러개의 객체를 생성하는것을 막음
		// 메소드를 사용해서만 객체를 생성(접군?)할 수 있다.
		// 단 하나의 객체만 생성해서 사용해야하는 특정클래스에 적용하는 패턴이다.
	
	// 1. private static으로 객체를 생성
	private static LogonDBBean instance = new LogonDBBean();
	
	// 2. 생성된 객체를 메소드로 공유
		// LogonDBBean 객체를 리턴하는 메소드
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	// 3. 기본생성자를 private으로 셋팅 : 외부에서 객체 생성이 불가능하도록 셋팅
		// 부모클래스(ConnPool)가 생성된다.
	private LogonDBBean() {
		super();
	};
	
	
	// 회원가입 처리 (registerPro.jsp)에서 넘어오는 값을 DB에 저장 (Insert)
	public void insertMember (LogonDataBean member) {
		SHA256 sha = SHA256.getInsatnce();
		
		try {
			// form에서 넘겨받은 Password를 DB에 저장하기 전 암호화 
			String orgPass = member.getPasswd();							// orgPass : form에서 넘겨받은 password
			String shaPass = sha.Sha256Encrypt(orgPass.getBytes());			// 
			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());		// bcPass : 암호화된 암호
			
			System.out.println ("암호화 되지 않은 패스워드 : " + orgPass);
			System.out.println ("암호화 된 패스워드 : " + bcPass);
			
			
			String sql = "INSERT INTO member01 VALUES (?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getPasswd());	// 암호화 되지 않은 password 저장
			// psmt.setString(2, bcPass);			// 암호화된 password 저장
			psmt.setString(3, member.getName());
			psmt.setTimestamp(4, member.getReg_date());
			psmt.setString(5, member.getAddress());
			psmt.setString(6, member.getTel());
			
			psmt.executeUpdate();
			
			System.out.println("회원 정보 DB 입력성공");
			
		} catch(Exception e) {
			e.getStackTrace();
			System.out.println("회원 정보를 DB에 입려가는 과정에서 예외발생");
		} finally {
			instance.close();
		}
	}
	
	
	
	// 로그인 처리 (loginPro.jsp) : form에서 넘겨받은 아이디와 패스워드를 DB와 비교 및 확인.
		// 사용자 인증처리, DB의 정보를 수정 및 삭제 할 때 사용
		// MemberCheck.jsp 에서 사용하는 메소드
	public int userCheck(String id, String passwd) {
		// x = -1인 경우, Select 된 ID가 없다라는 뜻
		// x = 1인 경우, 인증 성공
		int x = -1;		// 초기값
		
		// 복호화 : 암호화된 Password를 해독
		SHA256 sha = SHA256.getInsatnce();
		
		try {
			// form에서 넘겨온 password
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "SELECT * FROM member01 WHERE id = ?";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			
			if (rs.next()) {
				// id가 존재한다면
				String dbpasswd = rs.getString("passwd");			// DB에서 가져온 패스워드
				if (BCrypt.checkpw(shaPass, dbpasswd)) {
					// form에서 넘겨온 패스워드와 DB에서 가져온 패스워드가 일치 할 때
					x = 1;
				} else {
					// form에서 넘겨온 패스워드와 DB에서 가져온 패스워드가 일치하지 않을 때
					x = -1;
				}
				
			} else {
				System.out.println("ID가 DB에 존재하지 않습니다.");
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원정보 인증 실패");
		} finally {
			// 객체 사용 종료 (rs, stmt, psmt, con)
			instance.close();
		}
		
		return x;
	}
	
	
	
	// 아이디 중복 체크 (confirmId.jsp) : 아이디 중복을 확인하는 메서드
	public int confirmId (String id) {
		// x = -1인 경우, Select 된 ID가 없다라는 뜻 (중복된 ID가 없다라는 뜻)
		// x = 1인 경우, Select 된 ID가 있다라는 뜻 (중복된 ID가 있다라는 뜻)
		int x = -1;		// 초기값
		
		try {
			String sql = "SELECT * FROM member01 WHERE id = ?";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				x = 1;
			} else {
				x = -1;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("ID 중복 체크중 예외 발생");
		} finally {
			instance.close();
		}
		
		return x;
	}
	
	// 회원정보 수정 (modify.jsp) : DB에서 회원 정보의 값을 가져오는 메서드
	public LogonDataBean getMember (String id, String passwd) {
		// select한 레코드를 담는 DTO
		LogonDataBean member = null;
		
		SHA256 sha = SHA256.getInsatnce();
		
		
		try {
			String orgPass = passwd;							// orgPass : form에서 넘겨받은 password
			String shaPass = sha.Sha256Encrypt(orgPass.getBytes());
			
			String sql = "SELECT * FROM member01 WHERE id = ?";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				// id가 존재한다면
				String dbpasswd = rs.getString("passwd");			// DB에서 가져온 패스워드
				
				if (BCrypt.checkpw(shaPass, dbpasswd)) {
					// form에서 넘겨온 패스워드와 DB에서 가져온 패스워드가 일치 할 때
					
					// 데이터를 담기 위해 DTO 객체 생성
					member = new LogonDataBean();
					
					// 데이터 삽입
					member.setId(rs.getString("id"));
					member.setPasswd(rs.getString("passwd"));
					member.setName(rs.getString("name"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setAddress(rs.getString("address"));
					member.setTel(rs.getString("tel"));
					
				} else {
					// form에서 넘겨온 패스워드와 DB에서 가져온 패스워드가 일치하지 않을 때
					System.out.println("회원 정보가 일치하지 않습니다.");
				}
				
			} else {
				System.out.println("ID가 DB에 존재하지 않습니다.");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원 정보 읽어 오는 중 예외 발생");
		} finally {
			instance.close();
		}
		
		// member (LogonDataBean) : setter로 데이터 주입 된 DTO
		return member;
	}
	
	// 수정페이지에서 수정한 내용을 DB에 저장하는 메소드
	// 회원 정보 수정 처리 (modifyPro.jsp) 에서 회원정보 수정 처리하는 메소드 사용
	public int updateMember(LogonDataBean member) {
		// x = -1 일 경우, 업데이트 실패
		// x = 1 일 경우, 업데이트 성공
		int x = -1;
		
		// ID와 Password 확인 후 업데이트 진행
		SHA256 sha = SHA256.getInsatnce();
		
		try {
			String orgPass = member.getPasswd();
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "SELECT passwd FROM member01 WHERE id = ?";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, member.getId());
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				// 해당 id가 DB에 존재할 때
				String dbpasswd = rs.getString("passwd");
				
				// 두 패스워드가 일치할 때
				if (BCrypt.checkpw(shaPass, dbpasswd)) {
					// DTO(member)에서 들어온 값을 DB에 Insert
					sql = "UPDATE member01 set name = ?, address = ?, tel = ? WHERE id = ?";
					
					psmt = con.prepareStatement(sql);
					
					psmt.setString(1, member.getName());
					psmt.setString(2, member.getAddress());
					psmt.setString(3, member.getTel());
					psmt.setString(4, member.getId());
					
					psmt.executeUpdate();
					x = 1;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 정보 수정시 예외 발생");
		} finally {
			instance.close();
		}
		
		return x;
	}
	
	
	
	// 회원 탈퇴 처리 (deletePro.jsp 에서 호출되는 메서드) : 회원 정보 삭제 메서드
	public int deleteMember(String id, String passwd) {
		// x = -1 : 탈퇴 실패
		// x = 1 : 탈퇴 성공
		int x = -1;		// 초기값 -1
		
		SHA256 sha = SHA256.getInsatnce();
		
		try {
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "SELECT passwd FROM member01 WHERE id = ?";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				// ID가 DB에 존재하는 경우
				String dbpasswd = rs.getString("passwd");
				
				if (BCrypt.checkpw(shaPass, dbpasswd)) {
					sql = "DELETE member01 WHERE id = ?";
					psmt = con.prepareStatement(sql);
					psmt.setString(1, id);
					psmt.executeUpdate();
					
					x = 1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원 정보 삭제 처리 중 예외발생");
		} finally {
			instance.close();
		}
		
		return x;
	}
	
}
