package kr.co.cl.or.kh;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardDelete {
	private String deleteTitle;
	private Connection conn;
	private Statement stmt;
	private String sql;
	private int cnt;
	
	public BoardDelete() {
		
	}
	
	public void setDeleteTitle() {
		System.out.println("게시판 글 삭제");
		System.out.print("삭제할 게시글의 제목을 입력하세요 : ");
		deleteTitle = BoardRegister.scan.next();
	}
	
	public void boardDeleteSql() throws SQLException{
		conn = BoardRegister.getConnection();
		stmt = conn.createStatement(); //3.준비(Statement, PrepareStatement) 3-1 공간을 준비 
		sql = "delete from board where title = '"+deleteTitle+"'"; //3.준비 3-2 쿼리를 준비 ]
	}
	
	public void executeSql() throws SQLException{
		cnt = stmt.executeUpdate(sql); //4.실행 execute
		if(cnt==0) {
			System.out.println("해당하는 데이터가 존재하지 않습니다.");
		}
		else{
			System.out.println(cnt + "건 게시글이 삭제되었습니다.");
		}
	}

	/*
	public static void main(String[] args) {
		BoardDelete bd = new BoardDelete();
		bd.setDeleteTitle();
		try {
			bd.boardDeleteSql();
			bd.executeSql();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}