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
		System.out.println("게시판 글 작성");
		System.out.println("제목|내용 입력");
		titleContent = scan.next();
	}
	
	public void titleContentProcess() {
		indexI = titleContent.indexOf("|");
		title = titleContent.substring(0,indexI);
		content = titleContent.substring(indexI+1);	
	}
	
	public void setAuthor() {
		System.out.println("작성자 입력: ");
		author = scan.next();
	}
	
	public void setNal() {
		System.out.println("날짜 입력: ");
		nal = scan.next();
	}
	
	public void setReadCount() {
		System.out.println("조회수 입력: ");
		readcount = scan.nextInt();
	}
	
	public void boardQuery() throws SQLException{
		stmt = conn.createStatement(); //3.준비(Statement, PrepareStatement) 3-1 공간을 준비 
		sql = "insert into board(no,title,content,author,nal,readcount) values(board_no.nextval,'"+title+"','"+content+"','"+author+"','"+nal+"','"+readcount+"')"; //3.준비 3-2 쿼리를 준비 oracle
		//sql = "insert into board(title,content,author,nal,readcount) values('"+title+"','"+content+"','"+author+"','"+nal+"','"+readcount+"')"; //mysql
	}
	
	public void boardExecuter() throws SQLException{
		cnt = stmt.executeUpdate(sql); //4.실행 execute
		System.out.println(cnt + "건 게시글이 등록되었습니다.");
	}
	
	public BoardRegister(){
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver"); //1.로드(적재=자바에게 내가 데이터베이스를 뭘 쓰겠다.) 
			Class.forName("com.mysql.jdbc.Driver"); //1.로드(적재=자바에게 내가 데이터베이스를 뭘 쓰겠다.) mysql
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
