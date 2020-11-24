package kr.co.cl.or.kh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardRegister {
	public static Scanner scan;
	public static Connection conn;
	private String titleContent;
	private int indexI;
	private String title;
	private String content;
	private String author;
	private String nal;
	private int readcount;
	private Statement stmt;
	private String sql;
	private int cnt;
	
	static {
		scan = new Scanner(System.in);
	}
	
	public static Connection getConnection() throws SQLException{
		conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass","dkdlxl");
		//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8", "root", "");
		return conn;
	}
	
	public void setTitleContent() {
		System.out.println("�Խ��� �� �ۼ�");
		System.out.println("����|���� �Է�");
		titleContent = scan.next();
	}
	
	public void titleContentProcess() {
		indexI = titleContent.indexOf("|");
		title = titleContent.substring(0,indexI);
		content = titleContent.substring(indexI+1);	
	}
	
	public void setAuthor() {
		System.out.println("�ۼ��� �Է�: ");
		author = scan.next();
	}
	
	public void setNal() {
		System.out.println("��¥ �Է�: ");
		nal = scan.next();
	}
	
	public void setReadCount() {
		System.out.println("��ȸ�� �Է�: ");
		readcount = scan.nextInt();
	}
	
	public void boardQuery() throws SQLException{
		stmt = conn.createStatement(); //3.�غ�(Statement, PrepareStatement) 3-1 ������ �غ� 
		sql = "insert into board(no,title,content,author,nal,readcount) values(board_no.nextval,'"+title+"','"+content+"','"+author+"','"+nal+"','"+readcount+"')"; //3.�غ� 3-2 ������ �غ� oracle
		//sql = "insert into board(title,content,author,nal,readcount) values('"+title+"','"+content+"','"+author+"','"+nal+"','"+readcount+"')"; //mysql
	}
	
	public void boardExecuter() throws SQLException{
		cnt = stmt.executeUpdate(sql); //4.���� execute
		System.out.println(cnt + "�� �Խñ��� ��ϵǾ����ϴ�.");
	}
	
	public BoardRegister(){
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver"); //1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �� ���ڴ�.) 
			Class.forName("com.mysql.jdbc.Driver"); //1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �� ���ڴ�.) mysql
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	/*
	public static void main(String[] args) {
		BoardRegister reg = new BoardRegister();
		Connection conn = null;
		reg.setTitleContent();
		reg.titleContentProcess();
		reg.setAuthor();
		reg.setNal();
		reg.setReadCount();
		try {
			conn = reg.getConnection();
			reg.boardQuery();
			reg.boardExecuter();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
