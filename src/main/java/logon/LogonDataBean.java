package logon;

import java.sql.Timestamp;

// DTO, VO, Bean (DTO, DAO) : 백엔드에서 처리(로직을 처리)하는 java 페이지

// DTO(VO) 와 같다.
// 데이터를 Setter로 받아 Getter로 반환
public class LogonDataBean {
	// DB 컬럼
	private String id;
	private String passwd;
	private String name;
	private Timestamp reg_date;			// 가입 날짜의 시간을 등록
	private String address;
	private String tel;
	
	// getter & setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
