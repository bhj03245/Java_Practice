package kh.java.func;

import java.util.Scanner;

public class CalcView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Calc c = new CalcImpl();

		while(true) {
			System.out.println("첫번째 정수를 입력하세요 : ");
			double num1 = scan.nextInt();
			
			System.out.println("두번째 정수를 입력하세요 : ");
			double num2 = scan.nextInt();
			
			System.out.println("연산자를 선택해주세요(+,-,*,/,%) : ");
			char oper = scan.next().charAt(0);
			
			switch(oper) {
				case '+':
					double sum = c.sum(num1, num2);
					System.out.printf("더하기의 결과는 %.0f 입니다.%n",sum);
					break;
				case '-':
					double sub = c.sub(num1, num2);
					System.out.printf("빼기의 결과는 %.0f 입니다.%n",sub);
					break;
				case '*':
					double mul = c.mul(num1, num2);
					System.out.printf("곱하기의 결과는 %.0f 입니다.%n",mul);
					break;
				case '/':
					double div = c.div(num1, num2);
					System.out.printf("나누기의 결과는 %.2f 입니다.%n",div);
					break;
				case '%':
					double rem = c.rem(num1, num2);
					System.out.printf("첫번째 수를 두번째 수로 나눈 나머지는 %.0f 입니다.%n",rem);
					break;
				default:
					System.out.println("연산자를 다시 입력해주세요. ");
					break;
				}
		}	
	}

}
