package testProject;

import java.util.Scanner;

public class Apart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("----- A 아파트 정보 입력 -----");
		System.out.print("건물 총 층수 입력 : ");
		int floor = scan.nextInt();
		System.out.println(floor + "층이 생성 되었습니다.");
		String apart[][] = new String[floor][];
		
		for(int i=0;i<apart.length;i++) {
			System.out.print((i+1) + "층의 총 호실 입력 : ");
			int num = scan.nextInt();
			apart[i] = new String[num];
		}
		
		for(int i=0;i<apart.length;i++) {
			System.out.println((i+1)+"층은 "+apart[i].length+"호실까지 생성 되었습니다");
		}
		
		for(int i=0;i<apart.length;i++) {
			System.out.println("==========" + (i+1) +"층 정보 입력 ==========");
			for(int j=0;j<apart[i].length;j++) {
				System.out.print((i+1) + "층 " + (j+1) + "호 입주자 : ");
				String tenant = scan.next();
				apart[i][j] = tenant;
			}
		}
		
		System.out.println("----------- 각 층 정보 입력 -----------");
		for(int i=0;i<apart.length;i++) {
			System.out.print((i+1) + "층 : " );
			for(int j=0;j<apart[i].length;j++) {
				System.out.print(apart[i][j] + " ");
			}
			System.out.println();
		}
		
		scan.close();
		
	}

}
