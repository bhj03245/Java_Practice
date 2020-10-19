package kh.java.func;

public class CalcImpl implements Calc{
	@Override
	public double sum(double num1, double num2) {
		return (num1+num2);
	}
	
	@Override
	public double sub(double num1, double num2) {
		return (num1-num2);
	}
	
	@Override
	public double mul(double num1, double num2)
	{
		return (num1*num2);
	}
	
	@Override
	public double div(double num1, double num2) {
		return (num1/num2);
	}
	
	@Override
	public double rem(double num1, double num2) {
		return (num1%num2);
	}
}
