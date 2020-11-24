package kr.co.cl.or.kh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardSearch {
	private Connection conn;
	private String searchTitle;
	private Statement stmt;
	private String sql;
	private ResultSet rs;
	private int newReadCount;
	
	public void setSearchTitle() {
		System.out.println("게시판 글 검색");
		System.out.print("검색할 제목을 입력하세요. : ");
		searchTitle = BoardRegister.scan.next();
	}
	
	public void boardQuery() throws SQLException {
		conn = BoardRegister.getConnection();
		stmt = conn.createStatement(); //3.준비(Statement, PrepareStatement) 3-1 공간을 준비 
		sql = "select * from board where title = '"+searchTitle+"'"; //3.준비 3-2 쿼리를 준비 ]	
	}
	
	public void boardSearchExecute() throws SQLException{
		rs = stmt.executeQuery(sql);
	}
	
	public void boardSearchProcess() throws SQLException{
		while(rs.next()) {
			if(rs==null && !rs.isBeforeFirst()) {
				System.out.println("해당하는 데이터가 존재하지않습니다.");
			}
			newReadCount = rs.getInt(6);
			newReadCount++;
			System.out.println("번호\t제목\t내용\t작성자\t작성일자\t\t조회수");
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3) +"\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + newReadCount);
		}
	}
	
	public void countBoard() throws SQLException{
		sql = "update board set readcount = "+newReadCount+" where title = '"+searchTitle+"'";
		stmt.executeUpdate(sql);
	}
	
	public BoardSearch(){
	
	}
	
	/*
	public static void main(String[] args) {
		BoardSearch src =  new BoardSearch();
		src.setSearchTitle();
		try {
			src.boardQuery();
			src.boardSearchExecute();
			src.boardSearchProcess();
			src.countBoard();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}
