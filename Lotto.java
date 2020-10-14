package testProject;

import java.util.Scanner;

public class Lotto {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int lotto[] = new int[7];
		int choose[] = new int[7];
		int chk;
		choose = choose();
		lotto = mk_random();
		chk = compare(choose, lotto);
		chk_overlap(lotto);
		chk_overlap(choose);
		asc(lotto);
		asc(choose);
		
		System.out.print("로또번호 : ");
		
		for(int i=0;i<lotto.length;i++) {
			System.out.print(lotto[i] + " ");
		}
		
		System.out.println();
		System.out.print("응모번호 : ");
		
		for(int i=0;i<lotto.length;i++) {
			System.out.print(choose[i] + " ");
		}
		
		System.out.println();
		System.out.println("맞춘 개수 : " + chk);
	}
	
	public static int[] mk_random() {
		int random[] = new int[7];
		for(int i=0;i<7;i++) {
			random[i] = (int)(Math.random()*45+1);
		}
		return random;
	}
	
	public static void chk_overlap(int[] random) {
		for(int i=0;i<random.length;i++) {
			for(int j=0;j<i;j++) {
				if(random[i]==random[j]) {
					i--;
					break;
				}
			}
		}
	}
	
	public static void asc(int[] random) {
		for(int i=0;i<random.length;i++) {
			for(int j=i+1;j<random.length;j++) {
				if(random[i]>random[j]) {
					int tmp = random[i];
					random[i] = random[j];
					random[j] = tmp;
				}
			}
		}
	}
	
	public static int[] choose() {
		Scanner scan = new Scanner(System.in);
		int choose[] = new int[7];
		for(int i=0;i<7;i++) {
			System.out.println((i+1)+"번째 응모할 번호를 입력하세요 : ");
			choose[i] = scan.nextInt();
		}
		return choose;
	}
	
	public static int compare(int[] choose, int[] random) {
		int chk =0;
		for(int i=0;i<choose.length;i++) {
			for(int j=0;j<random.length;j++) {
				if(choose[i]==random[j]) {
					chk++;
				}
			}
		}
		return chk;
	}
			
}
