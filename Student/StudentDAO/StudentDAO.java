package StudentDAO;

import java.util.Scanner;
import StudentVO.Student;

public class StudentDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student[] stu = new Student[11];
		Scanner scan = new Scanner(System.in);
		System.out.println("학생 정보 관리 시스템 ");
		int num = 0;
		
			while(true) {
				System.out.println("1. 학생 정보 입력, 2. 전체 학생 정보 출력 3. 학생 정보 출력 4. 학생 정보 삭제  5. 학생 정보 수정 6. 종료");
				int sel = scan.nextInt();
				switch(sel){
					case 1:
						if(num < 10) {
							System.out.println("학생의 정보를 입력하세요.");
							System.out.print("학번 : ");
							int studno = scan.nextInt();
							
							System.out.print("이름 : ");
							String name = scan.next();
							
							System.out.print("나이 : ");
							int age = scan.nextInt();
							
							System.out.print("부서 : ");
							String depart = scan.next();
							
							stu[num] = new Student(studno, name, age, depart);
							num++;
							break;
						}
						else {
							System.out.println("저장 가능한 학생 수를 넘었습니다. 학생 정보를 삭제하고 다시 입력하세요");
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
						System.out.println("출력하려는 학생의 학번을 입력하세요.");
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
						System.out.println("삭제하려는 학생의 학번을 입력하세요.");
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
						System.out.println("수정하려는 학생의 학번을 입력하세요.");
						int u_input = scan.nextInt();
						System.out.println("학생의 정보를 수정하세요.");
						System.out.print("학번 : ");
						int studno = scan.nextInt();
						
						System.out.print("이름 : ");
						String name = scan.next();
						
						System.out.print("나이 : ");
						int age = scan.nextInt();
						
						System.out.print("부서 : ");
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
						System.out.println("시스템을 종료합니다.");
						return;
				}
		}
	}

}
