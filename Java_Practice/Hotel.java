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
				System.out.println("호텔 관리 프로그램 v1.0");
				System.out.println("1.입실	2.퇴실	3.방보기	4.종료");
				System.out.println("선택 >");
				int sel = scan.nextInt();
				
				switch(sel) {
				case 1:
					System.out.println("몇번 방에 입실하시겠습니까?");
					r = scan.nextInt();
					room[r]++;
	
					if(room[r]>=2) {
						System.out.println(r+"번 방에는 이미 손님이 있습니다.");
						room[r]--;
						break;
					}
					else {
						System.out.println(r + "방에 입실하셨습니다.");
						break;
					}
					
				case 2:
					System.out.println("몇번 방에 퇴실하시겠습니까?");
					r = scan.nextInt();
					if(room[r]>=1) {
						System.out.println(r + "번 방에서 퇴실하셨습니다.");
						room[r]--;
						break;
					}
					else {
						System.out.println(r + "번 방은 현재 빈방입니다.");
						break;
					}
					
				case 3:
					for(int i=1;i<room.length;i++) {
						if(room[i]>=1) {
							System.out.println(i+"번 방에 손님이 있습니다.");
							continue;
						}
						else {
							System.out.println(i+"번 방이 현재 비어있습니다.");
							continue;
						}
					}
					
				case 4:
					System.out.println("시스템을 종료합니다.");
					return;
					
				default:
					System.out.println("잘못 선택하셨습니다.");
				}
				
				try {
					Thread.sleep(2000); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("해당 방은 존재 하지 않습니다.");
		}
	}

}
