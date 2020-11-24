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
		System.out.println("�Խ��� �� �˻�");
		System.out.print("�˻��� ������ �Է��ϼ���. : ");
		searchTitle = BoardRegister.scan.next();
	}
	
	public void boardQuery() throws SQLException {
		conn = BoardRegister.getConnection();
		stmt = conn.createStatement(); //3.�غ�(Statement, PrepareStatement) 3-1 ������ �غ� 
		sql = "select * from board where title = '"+searchTitle+"'"; //3.�غ� 3-2 ������ �غ� ]	
	}
	
	public void boardSearchExecute() throws SQLException{
		rs = stmt.executeQuery(sql);
	}
	
	public void boardSearchProcess() throws SQLException{
		while(rs.next()) {
			if(rs==null && !rs.isBeforeFirst()) {
				System.out.println("�ش��ϴ� �����Ͱ� ���������ʽ��ϴ�.");
			}
			newReadCount = rs.getInt(6);
			newReadCount++;
			System.out.println("��ȣ\t����\t����\t�ۼ���\t�ۼ�����\t\t��ȸ��");
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
