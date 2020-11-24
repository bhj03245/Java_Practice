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
		System.out.print("�̸�:");
        System.out.print(name + "\t");
        System.out.print("����:");
        System.out.print(age + "\t");
        System.out.print("�й�:");
        System.out.print(hakbun + "\n");
	}
	
	public void listDispProfessor() {
		 System.out.print("�̸�:");
         System.out.print(name + "\t");
         System.out.print("����:");
         System.out.print(age + "\t");
         System.out.print("����:");
         System.out.print(subject + "\n");
	}
	
	public void listDispManager() {
		 System.out.print("�̸�:");
         System.out.print(name + "\t");
         System.out.print("����:");
         System.out.print(age + "\t");
         System.out.print("�μ�:");
         System.out.print(part + "\n");
	}
	
	public void listDispHaksa() throws SQLException{
		 s_age = rs.getInt("����");
         s_name = rs.getString("�̸�");
         s_hakbun = rs.getInt("�й�");
         p_age = rs.getInt("��������");
         p_name = rs.getString("�����̸�");
         p_subject = rs.getString("����");
         m_age = rs.getInt("�����ڳ���");
         m_name = rs.getString("�������̸�");
         m_part = rs.getString("�μ�");
         System.out.println("�л� ����: " + s_age + "\t�л� �̸�: " + s_name + "\t�й�: " + s_hakbun);
         System.out.println("���� ����: " + p_age + "\t���� �̸�: " + p_name + "\t����: " + p_subject);
         System.out.println("������ ����: " + m_age + "\t������ �̸�: " + m_name + "\t�μ�: " + m_part);
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
	      sql = "select s.no as ��ȣ,s.age as ����,s.name as �̸�,s.hakbun as �й�,p.age as ��������,p.name as �����̸�,p.subject as ����,m.age as �����ڳ���,m.name as �������̸�,m.part as �μ� from (student s full outer join professor p on s.no=p.no) full outer join manager m on s.no=m.no ORDER BY ��ȣ ASC";
	      System.out.println("�л���ü���");
	      rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	    	  listDispHaksa();
	      }
	}
	
	/* ���� �׽�Ʈ��
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

