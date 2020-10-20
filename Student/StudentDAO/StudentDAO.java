package StudentDAO;

import java.util.Scanner;
import StudentVO.Student;

public class StudentDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student[] stu = new Student[11];
		Scanner scan = new Scanner(System.in);
		System.out.println("�л� ���� ���� �ý��� ");
		int num = 0;
		
			while(true) {
				System.out.println("1. �л� ���� �Է�, 2. ��ü �л� ���� ��� 3. �л� ���� ��� 4. �л� ���� ����  5. �л� ���� ���� 6. ����");
				int sel = scan.nextInt();
				switch(sel){
					case 1:
						if(num < 10) {
							System.out.println("�л��� ������ �Է��ϼ���.");
							System.out.print("�й� : ");
							int studno = scan.nextInt();
							
							System.out.print("�̸� : ");
							String name = scan.next();
							
							System.out.print("���� : ");
							int age = scan.nextInt();
							
							System.out.print("�μ� : ");
							String depart = scan.next();
							
							stu[num] = new Student(studno, name, age, depart);
							num++;
							break;
						}
						else {
							System.out.println("���� ������ �л� ���� �Ѿ����ϴ�. �л� ������ �����ϰ� �ٽ� �Է��ϼ���");
							break;
						}
					case 2: 
						try {
							for(int i=0;i<stu.length;i++) {
									stu[i].PrintAll();
							}
							break;
						}catch(NullPointerException e) {
							break;
						}
					case 3:
						System.out.println("����Ϸ��� �л��� �й��� �Է��ϼ���.");
						int input = scan.nextInt();
						try {
							for(int i=0;i<stu.length;i++) {
								if(stu[i].getStudno() == input) {
										stu[i].PrintAll();
								}
							}
							break;
						}catch(NullPointerException e) {
							break;
						}
					case 4:
						System.out.println("�����Ϸ��� �л��� �й��� �Է��ϼ���.");
						int d_input = scan.nextInt();
						try {
							for(int i=0;i<stu.length;i++) {
								if(stu[i].getStudno() == d_input) {
									stu[i] = stu[10];
								}
							}
							break;
						}
						catch(NullPointerException e) {
							break;
						}
					
					case 5: 
						System.out.println("�����Ϸ��� �л��� �й��� �Է��ϼ���.");
						int u_input = scan.nextInt();
						System.out.println("�л��� ������ �����ϼ���.");
						System.out.print("�й� : ");
						int studno = scan.nextInt();
						
						System.out.print("�̸� : ");
						String name = scan.next();
						
						System.out.print("���� : ");
						int age = scan.nextInt();
						
						System.out.print("�μ� : ");
						String depart = scan.next();
						
						try {
							for(int i=0;i<stu.length;i++) {
								if(stu[i].getStudno() == u_input) {
									stu[i] = new Student(studno, name, age, depart);
								}
							}
							break;
						}
						catch(NullPointerException e) {
							break;
						}
					case 6:
						System.out.println("�ý����� �����մϴ�.");
						return;
				}
		}
	}

}
