package kr.co.kh.obj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AcademicDelete {
	private Statement stmt;
	private Connection conn;
	private String gubun;
	private String deleteName;
	private int cnt;
	
	public AcademicDelete() {
		
	}
	
	public void setGubun() throws IOException{
		System.out.println("�������: ");
		System.out.println("1.�л� 2.���� 3.������");
		gubun = AcademicRegister.input.readLine();
	}
	
	public void studentDeleteDisp() {
		System.out.println(cnt + "�� �л��� �����Ǿ����ϴ�.");
	}
	
	public void professorDeleteDisp() {
		System.out.println(cnt + "�� ������ �����Ǿ����ϴ�.");
	}
	
	public void managerDeleteDisp() {
		System.out.println(cnt + "�� �����ڰ� �����Ǿ����ϴ�.");
	}
	
	public void setDelete() throws IOException{
		System.out.println("������ �̸�: ");
		deleteName = AcademicRegister.input.readLine();
	}
	
	public void deleteProcess() throws IOException, SQLException{
		conn = AcademicRegister.getConnection();
		if(gubun.equals("1")) {
			setDelete();
			stmt = conn.createStatement();
			String sql = "delete from student where name = '"+deleteName+"'";
			cnt = stmt.executeUpdate(sql);
			studentDeleteDisp();
		}
		else if(gubun.equals("2")) {
			setDelete();
			stmt = conn.createStatement();
			String sql = "delete from professor where name = '"+deleteName+"'";
			cnt = stmt.executeUpdate(sql);
			professorDeleteDisp();
		}
		else if(gubun.equals("3")) {
			setDelete();
			stmt = conn.createStatement();
			String sql = "delete from manager where name = '"+deleteName+"'";
			cnt = stmt.executeUpdate(sql);
			managerDeleteDisp();
		}
	}
	
	public static void main(String[] args) {
		AcademicDelete del = new AcademicDelete();
		try {
			del.setGubun();
			del.deleteProcess();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
