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
			System.out.println("ȸ���� ������ �Է��ϼ���.");
			System.out.print("�̸� : ");
			String name = scan.next();
	
			System.out.print("����(1~100): ");
			int point = scan.nextInt();
			
			if(point > 100) {
				System.out.println("������ 100�� �����Դϴ�. �ٽ� �Է��ϼ���.");
				System.out.println("ȸ�� ���� �Է� ����");
				System.out.println();
			}
			else if(point>=90) {
				Vip vip = new Vip();
				double v_total = point + (point*vip.getInterest());
				g[num] = new Vip(name, "Vip", point, v_total);
				num++;
				System.out.println("ȸ�� ���� �Է��� �Ϸ� �Ǿ����ϴ�. ");
				System.out.println();
			}
			else if(point>=80) {
				Gold gold = new Gold();
				double g_total = point + (point*gold.getInterest());
				g[num] = new Gold(name, "Gold", point, g_total);
				num++;
				System.out.println("ȸ�� ���� �Է��� �Ϸ� �Ǿ����ϴ�. ");
				System.out.println();
			}
			else {
				Silver s = new Silver();
				double s_total = point + (point*s.getInterest());
				g[num] = new Silver(name, "Silver", point, s_total);
				num++;
				System.out.println("ȸ�� ���� �Է��� �Ϸ� �Ǿ����ϴ�. ");
				System.out.println();
			}
			
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("���̻� ȸ�� ������ ������ �� �����ϴ�.");
			}
		}
	
	@Override
	public void printAll() {
		System.out.println("��ü ȸ�� ���� ���");
		System.out.println("-----------------------------------");
		System.out.println("�̸�\t���\t����\t����\t");
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
		System.out.println("ȸ�� ���� ���");
		System.out.print("����Ϸ��� ȸ���� �̸��� �Է��ϼ��� : ");
		String input = scan.next();
		
		int s = searchData(input);
		
		if(s!=-1) {
			System.out.println("-----------------------------------");
			System.out.println("�̸�\t���\t����\t����\t");
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
				System.out.println("�ش� �����͸� ã�� �� �����ϴ�.");
				System.out.println();
			}
		return search;
	}
	
	@Override
	public void modifyData() {
		System.out.println("ȸ�� ���� ����");
		
		System.out.print("������ ȸ���� �̸��� �Է��ϼ��� : ");
		String update = scan.next();
		
		int search = searchData(update);
		
		if(search!=-1) {
			System.out.println("ȸ�� ������ �����ϼ���.");
			
			System.out.print("�̸� : ");
			String u_name = scan.next();
			
			System.out.print("���� : ");
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
			
			System.out.println("ȸ�� ���� ���� �Ϸ�");
			System.out.println("������ ������ =>");
			System.out.println("�̸� : " + g[search].getName());
			System.out.println("��� : " + g[search].getGrade());
			System.out.println("���� : " + g[search].getPoint());
			System.out.println("���� : " + g[search].getTotal());
			System.out.println();
		}
		
	}
	
	@Override
	public void deleteData() {
		System.out.println("ȸ�� ���� ����");
		
		System.out.print("������ ȸ���� �̸��� �Է��ϼ��� : ");
		String del = scan.next();
		
		int search = searchData(del);
		if(search != -1) {
			for(int i=search;i<num-1;i++) {
				g[i] = g[i+1];
			}
			num--;
			System.out.println(del + " ȸ�� ������ ������ �Ϸ�Ǿ����ϴ�.");
			System.out.println();
		}
	}
	
	@Override
	public void NumOfGrade() {
		int v_count=0, g_count=0, s_count=0;
		System.out.println("��� �� �ο��� ���");
		
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
		
		System.out.println("VIP ����� �ο��� : " + v_count + "��");
		System.out.println("Gold ����� �ο��� : " + g_count + "��");
		System.out.println("Silver ����� �ο��� : " + s_count + "��");
		System.out.println();
		
	}
	public void main() {
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("ȸ�� ���� ���� �ý��� ");
			System.out.println("0. ���� 1. ȸ�� ���� �Է� 2. ��ü ȸ�� ���� ��� 3. ȸ�� ���� ��� 4. ȸ������ ���� 5. ȸ������ ���� 6. ��޺� �ο��� ��� ");
			int sel = scan.nextInt();
			System.out.println();
			switch(sel) {
			case 0:
				System.out.println("�ý����� �����մϴ�.");
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
				System.out.println("�޴��� �߸� �����ϼ̽��ϴ�. �ٽ� ���� �ϼ���");
			}
		}
	}
}


