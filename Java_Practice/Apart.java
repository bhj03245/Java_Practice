package testProject;

import java.util.Scanner;

public class Apart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("----- A ����Ʈ ���� �Է� -----");
		System.out.print("�ǹ� �� ���� �Է� : ");
		int floor = scan.nextInt();
		System.out.println(floor + "���� ���� �Ǿ����ϴ�.");
		String apart[][] = new String[floor][];
		
		for(int i=0;i<apart.length;i++) {
			System.out.print((i+1) + "���� �� ȣ�� �Է� : ");
			int num = scan.nextInt();
			apart[i] = new String[num];
		}
		
		for(int i=0;i<apart.length;i++) {
			System.out.println((i+1)+"���� "+apart[i].length+"ȣ�Ǳ��� ���� �Ǿ����ϴ�");
		}
		
		for(int i=0;i<apart.length;i++) {
			System.out.println("==========" + (i+1) +"�� ���� �Է� ==========");
			for(int j=0;j<apart[i].length;j++) {
				System.out.print((i+1) + "�� " + (j+1) + "ȣ ������ : ");
				String tenant = scan.next();
				apart[i][j] = tenant;
			}
		}
		
		System.out.println("----------- �� �� ���� �Է� -----------");
		for(int i=0;i<apart.length;i++) {
			System.out.print((i+1) + "�� : " );
			for(int j=0;j<apart[i].length;j++) {
				System.out.print(apart[i][j] + " ");
			}
			System.out.println();
		}
		
		scan.close();
		
	}

}
