package kh.java.point.model.vo;

public class Gold extends Grade{
	
	public Gold() {}
	public Gold(String name, String grade, int point, double total) {
		super(name, grade, point, total);
	}
	
	@Override
	public double getInterest() {
		return 0.05;
	}
	
}
