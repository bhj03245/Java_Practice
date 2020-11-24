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
		System.out.println("�Խ��� �� ����");
		System.out.println("������ �Խñ��� ������ �Է��ϼ��� : ");
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
			System.out.println("��ȣ\t����\t����\t�ۼ���\t�ۼ�����\t\t��ȸ��");
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3) +"\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
		}
	}
	
	public char boardUpdateOption() {
		System.out.println("������ �����Ͻðڽ��ϱ�? Y/N");
		option = BoardRegister.scan.next().charAt(0);
		return option;
	}
	
	public void boardUpdateProcess() {
		if(option=='Y'||option=='y') {
			System.out.println("������ �Խñ��� ������ �Է��ϼ���");
			System.out.println("����|���� �Է�");
			titleContent = BoardRegister.scan.next();
			indexI = titleContent.indexOf("|");
			title = titleContent.substring(0,indexI);
			content = titleContent.substring(indexI+1);
			System.out.println("�ۼ��� �Է�: ");
			author = BoardRegister.scan.next();
			System.out.println("��¥ �Է�: ");
			nal = BoardRegister.scan.next();
			System.out.println("��ȸ�� �Է�: ");
			readcount = BoardRegister.scan.nextInt();
		}
	}
	
	public void boardUpdateFinal() throws SQLException{
		conn = BoardRegister.getConnection();
		stmt = conn.createStatement(); //3.�غ�(Statement, PrepareStatement) 3-1 ������ �غ� 
		sql = "update board set title = '"+title+"', content = '"+content+"', author = '"+author+"', nal = '"+nal+"', readcount = '"+readcount+"' where title = '"+updateTitle+"'"; //3.�غ� 3-2 ������ �غ� 
		cnt = stmt.executeUpdate(sql); //4.���� execute
		System.out.println(cnt + "�� �Խñ��� �����Ǿ����ϴ�.");
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

