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
		System.out.println("�Խ��� �� ����");
		System.out.print("������ �Խñ��� ������ �Է��ϼ��� : ");
		deleteTitle = BoardRegister.scan.next();
	}
	
	public void boardDeleteSql() throws SQLException{
		conn = BoardRegister.getConnection();
		stmt = conn.createStatement(); //3.�غ�(Statement, PrepareStatement) 3-1 ������ �غ� 
		sql = "delete from board where title = '"+deleteTitle+"'"; //3.�غ� 3-2 ������ �غ� ]
	}
	
	public void executeSql() throws SQLException{
		cnt = stmt.executeUpdate(sql); //4.���� execute
		if(cnt==0) {
			System.out.println("�ش��ϴ� �����Ͱ� �������� �ʽ��ϴ�.");
		}
		else{
			System.out.println(cnt + "�� �Խñ��� �����Ǿ����ϴ�.");
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