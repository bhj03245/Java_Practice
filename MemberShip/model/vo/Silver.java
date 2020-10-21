package kh.java.point.model.vo;

public class Silver extends Grade{
	public Silver() {}
	
	public Silver(String name, String grade, int point, double total) {
		super(name, grade, point, total);
	}
	
	@Override
	public double getInterest() {
		return 0.03;
	}
	
}
