package kr.co.kh.obj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcademicList {
	private Statement stmt;
	private Connection conn;
	private ResultSet rs;
	private String sql;
	private int no;
	private int age;
	private String name;
	private int hakbun;
	private String subject;
	private String part;
	private int s_age;
	private String s_name;
	private int s_hakbun;
	private int p_age;
	private String p_name;
	private String p_subject;
	private int m_age;
	private String m_name;
	private String m_part;
	
	public AcademicList() {
		
	}
	
	public void listDispStudent() {
		System.out.print("이름:");
        System.out.print(name + "\t");
        System.out.print("나이:");
        System.out.print(age + "\t");
        System.out.print("학번:");
        System.out.print(hakbun + "\n");
	}
	
	public void listDispProfessor() {
		 System.out.print("이름:");
         System.out.print(name + "\t");
         System.out.print("나이:");
         System.out.print(age + "\t");
         System.out.print("과목:");
         System.out.print(subject + "\n");
	}
	
	public void listDispManager() {
		 System.out.print("이름:");
         System.out.print(name + "\t");
         System.out.print("나이:");
         System.out.print(age + "\t");
         System.out.print("부서:");
         System.out.print(part + "\n");
	}
	
	public void listDispHaksa() throws SQLException{
		 s_age = rs.getInt("나이");
         s_name = rs.getString("이름");
         s_hakbun = rs.getInt("학번");
         p_age = rs.getInt("교수나이");
         p_name = rs.getString("교수이름");
         p_subject = rs.getString("과목");
         m_age = rs.getInt("관리자나이");
         m_name = rs.getString("관리자이름");
         m_part = rs.getString("부서");
         System.out.println("학생 나이: " + s_age + "\t학생 이름: " + s_name + "\t학번: " + s_hakbun);
         System.out.println("교수 나이: " + p_age + "\t교수 이름: " + p_name + "\t과목: " + p_subject);
         System.out.println("관리자 나이: " + m_age + "\t관리자 이름: " + m_name + "\t부서: " + m_part);
	}
	
	public void listProcess() throws IOException, SQLException{
		  conn = AcademicRegister.getConnection();
		  stmt = conn.createStatement();
	      sql = "select no,age,name,hakbun from student";
	      rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	         no = rs.getInt("no");
	         age = rs.getInt("age");
	         name = rs.getString("name");
	         hakbun = rs.getInt("hakbun");
	         listDispStudent();
	      }
	      stmt = conn.createStatement();
	      sql = "select no,age,name,subject from professor";
	      rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	         no = rs.getInt("no");
	         age = rs.getInt("age");
	         name = rs.getString("name");
	         subject = rs.getString("subject");
	         listDispProfessor();
	      }
	      stmt = conn.createStatement();
	      sql = "select no,age,name,part from manager";
	      rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	         no = rs.getInt("no");
	         age = rs.getInt("age");
	         name = rs.getString("name");
	         part = rs.getString("part");
	         listDispManager();
	      }
	      stmt = conn.createStatement();
	      sql = "select s.no as 번호,s.age as 나이,s.name as 이름,s.hakbun as 학번,p.age as 교수나이,p.name as 교수이름,p.subject as 과목,m.age as 관리자나이,m.name as 관리자이름,m.part as 부서 from (student s full outer join professor p on s.no=p.no) full outer join manager m on s.no=m.no ORDER BY 번호 ASC";
	      System.out.println("학사전체출력");
	      rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	    	  listDispHaksa();
	      }
	}
	
	/* 단위 테스트용
	public static void main(String[] args) {
		AcademicList list = new AcademicList();
		try {
			list.listProcess();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}

