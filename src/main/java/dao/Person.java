package dao;
public class Person {
	// 필드 (속성, 전역변수)
	// private : useBean의 getProperty와 setProperty로 접근 가능
		// 실제는 getter와 setter로 접근하는 것
	private int id = 20181004;				
	private String name = "홍길동";
	
	// 기본 생성자
	public Person() {}

	// getter & setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
}
