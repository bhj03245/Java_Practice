package testProject;

import java.util.Scanner;

public class Hotel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int[] room = new int[11];
		int r;
		try {
			while(true) {
				System.out.println("ȣ�� ���� ���α׷� v1.0");
				System.out.println("1.�Խ�	2.���	3.�溸��	4.����");
				System.out.println("���� >");
				int sel = scan.nextInt();
				
				switch(sel) {
				case 1:
					System.out.println("��� �濡 �Խ��Ͻðڽ��ϱ�?");
					r = scan.nextInt();
					room[r]++;
	
					if(room[r]>=2) {
						System.out.println(r+"�� �濡�� �̹� �մ��� �ֽ��ϴ�.");
						room[r]--;
						break;
					}
					else {
						System.out.println(r + "�濡 �Խ��ϼ̽��ϴ�.");
						break;
					}
					
				case 2:
					System.out.println("��� �濡 ����Ͻðڽ��ϱ�?");
					r = scan.nextInt();
					if(room[r]>=1) {
						System.out.println(r + "�� �濡�� ����ϼ̽��ϴ�.");
						room[r]--;
						break;
					}
					else {
						System.out.println(r + "�� ���� ���� ����Դϴ�.");
						break;
					}
					
				case 3:
					for(int i=1;i<room.length;i++) {
						if(room[i]>=1) {
							System.out.println(i+"�� �濡 �մ��� �ֽ��ϴ�.");
							continue;
						}
						else {
							System.out.println(i+"�� ���� ���� ����ֽ��ϴ�.");
							continue;
						}
					}
					
				case 4:
					System.out.println("�ý����� �����մϴ�.");
					return;
					
				default:
					System.out.println("�߸� �����ϼ̽��ϴ�.");
				}
				
				try {
					Thread.sleep(2000); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("�ش� ���� ���� ���� �ʽ��ϴ�.");
		}
	}

}
