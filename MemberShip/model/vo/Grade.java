package kh.java.point.model.vo;

public abstract class Grade {
	String name;
	String grade;
	int point;
	double total;
	
	public Grade() {}
	public Grade(String name, String grade, int point, double total) {
		this.name = name;
		this.grade = grade;
		this.point = point;
		this.total = total;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public abstract double getInterest();
	
	@Override
	public String toString() {
		return getName()+"\t"+getGrade()+"\t"+getPoint()+"\t"+getTotal();
	}
	
}
