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
				//Class.forName("oracle.jdbc.driver.OracleDriver"); //1.로드(적재=자바에게 내가 데이터베이스를 뭘 쓰겠다.) 
				//Class.forName("com.mysql.jdbc.Driver"); //1.로드(적재=자바에게 내가 데이터베이스를 뭘 쓰겠다.) mysql
				//conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass","dkdlxl");
				//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8", "root", "");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			System.out.println();
			System.out.println("====== 게시판 작성 =======");
			System.out.println("R:등록 S:검색 D:삭제 U:수정 L:목록 E:종료");
			char sel = scan.next().charAt(0); //메뉴 선택 	
			
			if(sel=='R'||sel=='r') { //등록
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
			
			else if(sel=='S'||sel=='s') { //검색
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
			
			else if(sel=='D'||sel=='d') { //삭제
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
			
			else if(sel=='U'||sel=='u') { //수정
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
						System.out.println("Y 혹은 N 중 하나를 입력하세요.");
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
			
			else if(sel=='L'||sel=='l') { //전체 데이터 출력
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
				System.out.println("시스템 종료");
				try {
					conn.close();
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			else {
				System.out.println("메뉴 선택이 잘못되었습니다. 다시 선택 하세요");
			}
		}	
	}

}
