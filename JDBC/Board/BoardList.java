package kr.co.cl.or.kh;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardList {
	private Connection conn;
	private Statement stmt;
	private String sql;
	private ResultSet rs;
	
	public void boardStmtSql() throws SQLException {
		System.out.println("전체 데이터 출력");
		conn = BoardRegister.getConnection();
		stmt = conn.createStatement(); //3.준비(Statement, PrepareStatement) 3-1 공간을 준비 
		sql = "select * from board order by no"; //3.준비 3-2 쿼리를 준비 ]
	}
	
	public void boardListExecute() throws SQLException{
		rs = stmt.executeQuery(sql);
	}
	
	public void boardListProcess() throws SQLException{
		while(rs.next()) {
			System.out.println("번호\t제목\t내용\t작성자\t작성일자\t\t조회수");
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3) +"\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
			System.out.println();
		}
	}
	
	/*
	public static void main(String[] args) {
		BoardList list = new BoardList();
		try {
			list.boardStmtSql();
			list.boardListExecute();
			list.boardListProcess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}