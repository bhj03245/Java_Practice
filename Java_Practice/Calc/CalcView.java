package kh.java.func;

import java.util.Scanner;

public class CalcView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Calc c = new CalcImpl();

		while(true) {
			System.out.println("ù��° ������ �Է��ϼ��� : ");
			double num1 = scan.nextInt();
			
			System.out.println("�ι�° ������ �Է��ϼ��� : ");
			double num2 = scan.nextInt();
			
			System.out.println("�����ڸ� �������ּ���(+,-,*,/,%) : ");
			char oper = scan.next().charAt(0);
			
			switch(oper) {
				case '+':
					double sum = c.sum(num1, num2);
					System.out.printf("���ϱ��� ����� %.0f �Դϴ�.%n",sum);
					break;
				case '-':
					double sub = c.sub(num1, num2);
					System.out.printf("������ ����� %.0f �Դϴ�.%n",sub);
					break;
				case '*':
					double mul = c.mul(num1, num2);
					System.out.printf("���ϱ��� ����� %.0f �Դϴ�.%n",mul);
					break;
				case '/':
					double div = c.div(num1, num2);
					System.out.printf("�������� ����� %.2f �Դϴ�.%n",div);
					break;
				case '%':
					double rem = c.rem(num1, num2);
					System.out.printf("ù��° ���� �ι�° ���� ���� �������� %.0f �Դϴ�.%n",rem);
					break;
				default:
					System.out.println("�����ڸ� �ٽ� �Է����ּ���. ");
					break;
				}
		}	
	}

}
