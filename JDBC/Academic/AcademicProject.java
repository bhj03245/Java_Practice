package kr.co.kh.obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcademicProject {

	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String protocol=null;
		Connection conn=null;
		AcademicRegister register = new AcademicRegister();
		AcademicSearch search = new AcademicSearch();
		AcademicDelete delete = new AcademicDelete();
		AcademicList list = new AcademicList();		
		while(true) {//�ݺ���
		System.out.println("======�л����=====");
		System.out.println("1.���");
		System.out.println("2.�˻�");
		System.out.println("3.����");
		System.out.println("4.��ü���");
		System.out.println("=================");
		System.out.println("0.����");
		System.out.println("��ȣ�� ������ �ּ���..");	
		try {
			protocol = input.readLine();
			 conn= AcademicRegister.getConnection();
		} catch (IOException e1) {
			e1.printStackTrace();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}			
		if(protocol.equals("1")) {
			     try {
			    	 protocol=register.setInput();
				     register.haksaProcess();
			    } catch (IOException e) {
				e.printStackTrace();
			    } catch (SQLException e) {
				e.printStackTrace();
			    } finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}						
			    if(protocol.equals("4")) {
				continue;
			    }
			System.out.println("����Ͻ÷��� 1,�����Ͻ÷���0�� �Է��� �ּ���..");
			try {
				String bunho = input.readLine();
				if(bunho.equals("1")) {
					continue;
				}else {
					System.exit(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//����ǳ� 
		if(protocol.equals("2")) {
			System.out.println("ã��");
			   try {
				search.setGubun();
				search.searchProcess();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}			
		}
		else if(protocol.equals("3")) {
			System.out.println("����");
			try {
				delete.setGubun();
				delete.deleteProcess();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(protocol.equals("4")) {
			System.out.println("��ü���");
			        try {
						list.listProcess();						
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}			
		   }		
		else if(protocol.equals("0")) {			
			System.out.println("�л����α׷��� �����մϴ�.");
			System.exit(0);
		}
	 }//�ݺ���
	}
}
