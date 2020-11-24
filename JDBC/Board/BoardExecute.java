package kr.co.cl.or.kh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardExecute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoardRegister reg = new BoardRegister();
		BoardSearch search = new BoardSearch();
		BoardDelete del = new BoardDelete();
		BoardUpdate up = new BoardUpdate();
		BoardList list = new BoardList();
		
		Scanner scan = new Scanner(System.in);
		Connection conn =null;
		
		while(true) {
			try {
				conn = reg.getConnection();
				//Class.forName("oracle.jdbc.driver.OracleDriver"); //1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �� ���ڴ�.) 
				//Class.forName("com.mysql.jdbc.Driver"); //1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �� ���ڴ�.) mysql
				//conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass","dkdlxl");
				//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8", "root", "");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			System.out.println();
			System.out.println("====== �Խ��� �ۼ� =======");
			System.out.println("R:��� S:�˻� D:���� U:���� L:��� E:����");
			char sel = scan.next().charAt(0); //�޴� ���� 	
			
			if(sel=='R'||sel=='r') { //���
				try {
					reg.setTitleContent();
					reg.titleContentProcess();
					reg.setAuthor();
					reg.setNal();
					reg.setReadCount();
					reg.boardQuery();
					reg.boardExecuter();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			else if(sel=='S'||sel=='s') { //�˻�
				try {
					search.setSearchTitle();
					search.boardQuery();
					search.boardSearchExecute();
					search.boardSearchProcess();
					search.countBoard();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			else if(sel=='D'||sel=='d') { //����
				try {
					del.setDeleteTitle();
					del.boardDeleteSql();
					del.executeSql();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			else if(sel=='U'||sel=='u') { //����
				char option='n';
				try {
					up.boardUpdateTitle();
					up.boardUpdateSearch();
					up.boardUpdateExecuterSql();
					up.boardBeforeUpdate();
					option = up.boardUpdateOption();
					if(option == 'y' || option =='Y') {
						up.boardUpdateProcess();
						up.boardUpdateFinal();
					}
					else if(option == 'n' || option == 'N') {
						continue;
					}
					else {
						System.out.println("Y Ȥ�� N �� �ϳ��� �Է��ϼ���.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
			}
			
			else if(sel=='L'||sel=='l') { //��ü ������ ���
				try {
					list.boardStmtSql();
					list.boardListExecute();
					list.boardListProcess();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			else if(sel=='e' || sel=='E') {
				System.out.println("�ý��� ����");
				try {
					conn.close();
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			else {
				System.out.println("�޴� ������ �߸��Ǿ����ϴ�. �ٽ� ���� �ϼ���");
			}
		}	
	}

}
