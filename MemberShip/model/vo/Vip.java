package kh.java.point.model.vo;

public class Vip extends Grade{
	public Vip() {}
	
	public Vip(String name, String grade, int point, double total) {
		super(name, grade, point, total);
	}
	
	@Override
	public double getInterest() {
		return 0.1;
	}
	
	

}
