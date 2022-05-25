package membership03;

public class Member03DTO {
	// 멤버 변수 선언 (테이블의 컬러명)
		private String id;
		private String pass;
		private String name;
		private String regidate;
		private String grade;
		
		// getter, setter
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRegidate() {
			return regidate;
		}
		public void setRegidate(String regidate) {
			this.regidate = regidate;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
}
