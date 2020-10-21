package kh.java.point.controller;
import java.util.Scanner;

import kh.java.point.model.vo.Gold;
import kh.java.point.model.vo.Grade;
import kh.java.point.model.vo.Silver;
import kh.java.point.model.vo.Vip;

public class PntMgr implements PntMgrInterface{
	Scanner scan = new Scanner(System.in);
	Grade[] g = new Grade[20]; 
	int num=0;
	
	public PntMgr() {}
	
	@Override
	public void insertData() {
		try {
			System.out.println("회원의 정보를 입력하세요.");
			System.out.print("이름 : ");
			String name = scan.next();
	
			System.out.print("점수(1~100): ");
			int point = scan.nextInt();
			
			if(point > 100) {
				System.out.println("점수는 100점 까지입니다. 다시 입력하세요.");
				System.out.println("회원 정보 입력 실패");
				System.out.println();
			}
			else if(point>=90) {
				Vip vip = new Vip();
				double v_total = point + (point*vip.getInterest());
				g[num] = new Vip(name, "Vip", point, v_total);
				num++;
				System.out.println("회원 정보 입력이 완료 되었습니다. ");
				System.out.println();
			}
			else if(point>=80) {
				Gold gold = new Gold();
				double g_total = point + (point*gold.getInterest());
				g[num] = new Gold(name, "Gold", point, g_total);
				num++;
				System.out.println("회원 정보 입력이 완료 되었습니다. ");
				System.out.println();
			}
			else {
				Silver s = new Silver();
				double s_total = point + (point*s.getInterest());
				g[num] = new Silver(name, "Silver", point, s_total);
				num++;
				System.out.println("회원 정보 입력이 완료 되었습니다. ");
				System.out.println();
			}
			
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("더이상 회원 정보를 저장할 수 없습니다.");
			}
		}
	
	@Override
	public void printAll() {
		System.out.println("전체 회원 정보 출력");
		System.out.println("-----------------------------------");
		System.out.println("이름\t등급\t점수\t총점\t");
		System.out.println("-----------------------------------");
		for(int i=0;i<num;i++) {
			try {
			System.out.println(g[i].toString());
			}catch(NullPointerException e) {
				break;
			}
		}
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	@Override
	public void printData(){
		System.out.println("회원 정보 출력");
		System.out.print("출력하려는 회원의 이름을 입력하세요 : ");
		String input = scan.next();
		
		int s = searchData(input);
		
		if(s!=-1) {
			System.out.println("-----------------------------------");
			System.out.println("이름\t등급\t점수\t총점\t");
			System.out.println("-----------------------------------");
			System.out.println(g[s].toString());
			System.out.println();
		}
	}
	
	@Override
	public int searchData(String name) {
		int search=-1;
			for(int i=0;i<g.length;i++) {
				try {
					if(g[i].getName().equals(name)) {
						search = i;
					}
				}catch(NullPointerException e) {
					break;
				}
			}
			if(search==-1) {
				System.out.println("해당 데이터를 찾을 수 없습니다.");
				System.out.println();
			}
		return search;
	}
	
	@Override
	public void modifyData() {
		System.out.println("회원 정보 수정");
		
		System.out.print("수정할 회원의 이름을 입력하세요 : ");
		String update = scan.next();
		
		int search = searchData(update);
		
		if(search!=-1) {
			System.out.println("회원 정보를 수정하세요.");
			
			System.out.print("이름 : ");
			String u_name = scan.next();
			
			System.out.print("점수 : ");
			int u_point = scan.nextInt();
			
			System.out.println();
			
			if(u_point >= 90) {
				Vip vip = new Vip();
				double v_total = u_point + (u_point*vip.getInterest());
				g[search] = new Vip(u_name, "Vip", u_point, v_total);
			}
			else if(u_point >= 80) {
				Gold gold = new Gold();
				double g_total = u_point + (u_point*gold.getInterest());
				g[search] = new Gold(u_name, "Gold", u_point, g_total);
			}
			else{
				Silver s = new Silver();
				double s_total = u_point + (u_point*s.getInterest());
				g[search] = new Silver(u_name, "Silver", u_point, s_total);
			}
			
			System.out.println("회원 정보 수정 완료");
			System.out.println("수정된 데이터 =>");
			System.out.println("이름 : " + g[search].getName());
			System.out.println("등급 : " + g[search].getGrade());
			System.out.println("점수 : " + g[search].getPoint());
			System.out.println("총점 : " + g[search].getTotal());
			System.out.println();
		}
		
	}
	
	@Override
	public void deleteData() {
		System.out.println("회원 정보 삭제");
		
		System.out.print("삭제할 회원의 이름을 입력하세요 : ");
		String del = scan.next();
		
		int search = searchData(del);
		if(search != -1) {
			for(int i=search;i<num-1;i++) {
				g[i] = g[i+1];
			}
			num--;
			System.out.println(del + " 회원 정보의 삭제가 완료되었습니다.");
			System.out.println();
		}
	}
	
	@Override
	public void NumOfGrade() {
		int v_count=0, g_count=0, s_count=0;
		System.out.println("등급 별 인원수 출력");
		
		for(int i=0;i<g.length;i++) {
			try {
				if(g[i].getGrade().equals("Vip")) {
					v_count++;
				}
				else if(g[i].getGrade().equals("Gold")) {
					g_count++;
				}
				else if(g[i].getGrade().equals("Silver")) {
					s_count++;
				}
			}catch(NullPointerException e) {
				break;
			}
		}
		
		System.out.println("VIP 등급의 인원수 : " + v_count + "명");
		System.out.println("Gold 등급의 인원수 : " + g_count + "명");
		System.out.println("Silver 등급의 인원수 : " + s_count + "명");
		System.out.println();
		
	}
	public void main() {
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("회원 정보 관리 시스템 ");
			System.out.println("0. 종료 1. 회원 정보 입력 2. 전체 회원 정보 출력 3. 회원 정보 출력 4. 회원정보 수정 5. 회원정보 삭제 6. 등급별 인원수 출력 ");
			int sel = scan.nextInt();
			System.out.println();
			switch(sel) {
			case 0:
				System.out.println("시스템을 종료합니다.");
				return;
			case 1:
				insertData();
				break;
			case 2:
				printAll();
				break;
			case 3:
				printData();
				break;
			case 4:
				modifyData();
				break;
			case 5:
				deleteData();
				break;
			case 6:
				NumOfGrade();
				break;
			default:
				System.out.println("메뉴를 잘못 선택하셨습니다. 다시 선택 하세요");
			}
		}
	}
}


