package StudentVO;

public class Student {
	int studno;
	String name;
	int age;
	String depart;
	
	public Student() {}
	
	public Student(int studno, String name, int age, String depart) {
		this.studno = studno;
		this.name = name;
		this.age = age;
		this.depart = depart;
	}
	
	public int getStudno() {
		return studno;
	}
	public void setStudno(int studno) {
		this.studno = studno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	
	public void PrintAll() {
		System.out.println(name + " �л��� �й��� " + studno + " ���̴� " + age + "�� �̰� " + depart + "�� �ҼӵǾ��ֽ��ϴ�.");
	}
}
