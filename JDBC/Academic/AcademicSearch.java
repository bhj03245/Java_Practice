package kr.co.kh.obj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcademicSearch {
	private Statement stmt;
	private Connection conn;

	private String gubun;
	private String searchName;
	private int age;
	private String name;
	private int hakbun;
	private String subject;
	private String part;
	private String sql;
	private ResultSet rs;

	public AcademicSearch() {

	}

	public void setGubun() throws IOException {
		System.out.println("ã�����:");
		System.out.println("1.�л�2.����3.������");
		gubun = AcademicRegister.input.readLine();
	}

	public void setNameSearchStudent() throws IOException {
		System.out.println("ã�� �л��̸��Է�:");
		searchName = AcademicRegister.input.readLine();
	}

	public void setNameSearchProfessor() throws IOException {
		System.out.println("ã�� �����̸��Է�:");
		searchName = AcademicRegister.input.readLine();
	}

	public void setNameSearchManager() throws IOException {
		System.out.println("ã�� �������̸��Է�:");
		searchName = AcademicRegister.input.readLine();
	}

	public void studentDisp() {
		System.out.print("�̸�:" + name);
		System.out.print("����:" + age);
		System.out.print("�й�:" + hakbun + "\n");
	}

	public void professorDisp() {
		System.out.print("�̸�:" + name);
		System.out.print("����:" + age);
		System.out.print("����:" + subject + "\n");
	}

	public void managerDisp() {
		System.out.print("�̸�:" + name);
		System.out.print("����:" + age);
		System.out.print("�μ�:" + part + "\n");
	}

	public void searchProcess() throws IOException, SQLException {
		conn = AcademicRegister.getConnection();
		if (gubun.equals("1")) {
			setNameSearchStudent();
			stmt = conn.createStatement();
			sql = "select age,name,hakbun from student where name ='" + searchName + "'";
			rs = stmt.executeQuery(sql);
			if (!rs.isBeforeFirst()) {
				System.out.println("ã���л��� �����ϴ�.");
			}

			while (rs.next()) {
				name = rs.getString("name");
				age = rs.getInt("age");
				hakbun = rs.getInt("hakbun");
				studentDisp();
			}

		} else if (gubun.equals("2")) {

			setNameSearchProfessor();
			stmt = conn.createStatement();
			sql = "select age,name,subject from professor where name ='" + searchName + "'";
			rs = stmt.executeQuery(sql);
			if (!rs.isBeforeFirst()) {
				System.out.println("ã�±������� �����ϴ�.");
			}

			while (rs.next()) {
				name = rs.getString("name");
				age = rs.getInt("age");
				subject = rs.getString("subject");
				professorDisp();
			}
		} else if (gubun.equals("3")) {
			setNameSearchManager();
			stmt = conn.createStatement();
			sql = "select age,irum,part from manager where irum='" + searchName + "'";
			rs = stmt.executeQuery(sql);
			if (!rs.isBeforeFirst()) {
				System.out.println("ã�°����ڴ��� �����ϴ�.");
			}
			while (rs.next()) {
				name = rs.getString("name");
				age = rs.getInt("age");
				part = rs.getString("part");
				managerDisp();
			}
		}
	}

	public static void main(String[] args) {

		AcademicSearch sea = new AcademicSearch();
		try {
			sea.setGubun();
			sea.searchProcess();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}