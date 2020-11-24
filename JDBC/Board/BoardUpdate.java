package kr.co.cl.or.kh;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardUpdate {
	private String updateTitle;
	private Statement stmt;
	private Connection conn;
	private String sql;
	private ResultSet rs;
	private String titleContent;
	private int indexI;
	private String title;
	private String content;
	private String author;
	private String nal;
	private int readcount;
	private char option;
	private int cnt;
	
	public BoardUpdate() {
		
	}
	
	public void boardUpdateTitle() {
		System.out.println("게시판 글 수정");
		System.out.println("수정할 게시글의 제목을 입력하세요 : ");
		updateTitle = BoardRegister.scan.next();
	}
	
	public void boardUpdateSearch() throws SQLException{
		conn = BoardRegister.getConnection();
		stmt = conn.createStatement();
		sql = "select * from board where title = '"+updateTitle+"'";
	}
	
	public void boardUpdateExecuterSql() throws SQLException{
		rs = stmt.executeQuery(sql);
	}
	
	public void boardBeforeUpdate() throws SQLException{
		while(rs.next()) {
			System.out.println("번호\t제목\t내용\t작성자\t작성일자\t\t조회수");
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3) +"\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
		}
	}
	
	public char boardUpdateOption() {
		System.out.println("정말로 변경하시겠습니까? Y/N");
		option = BoardRegister.scan.next().charAt(0);
		return option;
	}
	
	public void boardUpdateProcess() {
		if(option=='Y'||option=='y') {
			System.out.println("수정할 게시글의 정보를 입력하세요");
			System.out.println("제목|내용 입력");
			titleContent = BoardRegister.scan.next();
			indexI = titleContent.indexOf("|");
			title = titleContent.substring(0,indexI);
			content = titleContent.substring(indexI+1);
			System.out.println("작성자 입력: ");
			author = BoardRegister.scan.next();
			System.out.println("날짜 입력: ");
			nal = BoardRegister.scan.next();
			System.out.println("조회수 입력: ");
			readcount = BoardRegister.scan.nextInt();
		}
	}
	
	public void boardUpdateFinal() throws SQLException{
		conn = BoardRegister.getConnection();
		stmt = conn.createStatement(); //3.준비(Statement, PrepareStatement) 3-1 공간을 준비 
		sql = "update board set title = '"+title+"', content = '"+content+"', author = '"+author+"', nal = '"+nal+"', readcount = '"+readcount+"' where title = '"+updateTitle+"'"; //3.준비 3-2 쿼리를 준비 
		cnt = stmt.executeUpdate(sql); //4.실행 execute
		System.out.println(cnt + "건 게시글이 수정되었습니다.");
	}
	
	/*
	public static void main(String[] args) throws SQLException {
		BoardUpdate bu = new BoardUpdate();
		bu.boardUpdateTitle();
		bu.boardUpdateSearch();
		bu.boardUpdateExecuterSql();
		bu.boardBeforeUpdate();
		bu.boardUpdateOption();
		bu.boardUpdateProcess();
		bu.boardUpdateFinal();
	}
	*/
}

