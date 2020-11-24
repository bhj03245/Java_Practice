package kr.co.kh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberExecute {
	public static String session;
	static {
		session = null;
	}
	
	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String protocol = null;
		Connection conn = null;
		int cnt = 0;
		while(true) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); //1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �� ���ڴ�.) 
				//Class.forName("com.mysql.jdbc.Driver"); //1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �� ���ڴ�.) mysql
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass","dkdlxl");
				//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8", "root", "");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("ȸ�� ����");
			System.out.println("R: ȸ������ L: ȸ�� ��� S: IDã�� D: ȸ�� Ż�� E: ȸ�� ���� I:�α��� O:�α׾ƿ�");
			try {
				protocol = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(protocol.equals("R")|| protocol.equals("r")) {
				try {
					System.out.println("���̵��Է�: ");
					String id = input.readLine();
					System.out.println("�н����� �Է�: ");
					String passwd = input.readLine();
					System.out.println("�ּ� �Է�: ");
					String addr = input.readLine();
					System.out.println("��ȭ��ȣ �Է�: ");
					String phone = input.readLine();
					try {
						Statement stmt = conn.createStatement();
						String sql = "insert into member(id,passwd,addr,phone) values('"+id+"','"+passwd+"','"+addr+"','"+phone+"')";
						cnt = stmt.executeUpdate(sql);
						System.out.println(cnt + "�� ȸ���� �ԷµǾ����ϴ�.");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println();
			}
			
			else if(protocol.equals("L") || protocol.equals("l")) {
				System.out.println("ȸ�� ��� ���");
				try {
					Statement stmt = conn.createStatement();
					String sql = "SELECT id,passwd,addr,phone from member";
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println("ȸ�����̵� \t ȸ���н����� \t ȸ���ּ� \t ȸ����ȭ��ȣ");
					while(rs.next()) {
						String id = rs.getString("id");
						String passwd = rs.getString("passwd");
						String addr = rs.getString("addr");
						String phone = rs.getString("phone");
						System.out.print(id+"\t"+passwd+"\t"+addr+"\t"+phone+"\n");
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println();
			}
			
			else if(protocol.equals("S") || protocol.equals("s")) {
				System.out.println("ȸ�� �˻�");
				System.out.println("�˻��� ���̵� �Է����ּ���.");
				try {
					String searchID = input.readLine();
					Statement stmt = conn.createStatement();
					String sql = "select * from member where id = '"+searchID+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(!rs.isBeforeFirst()) {
						System.out.println("ã�� ���̵� ���� ���� �ʽ��ϴ�.");
					}
					if(rs!=null) {
						while(rs.next()) {
							System.out.println("ȸ�����̵� \t ȸ���н����� \t ȸ���ּ� \t ȸ����ȭ��ȣ");
							System.out.print(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\n");
						}
					}				
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			else if(protocol.equals("D") || protocol.equals("d")) {
				System.out.println("ȸ�� Ż��");
				try {
					Statement stmt = conn.createStatement();
					String sql = "delete from member where id = '"+session+"'";
					cnt = stmt.executeUpdate(sql);
					if(cnt>=1) {
						System.out.println("ȸ�� Ż��Ǿ����ϴ�.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			else if(protocol.equals("E") || protocol.equals("e")) {
				System.out.println("ȸ�� ���� ����");
				try {
					Statement stmt = conn.createStatement();
					String sql = "select * from member where id = '"+session+"'";
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs!=null) {
						while(rs.next()) {
							System.out.println("ȸ�����̵� \t ȸ���н����� \t ȸ���ּ� \t ȸ����ȭ��ȣ");
							System.out.print(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\n");
						}
					}
					
					System.out.println("���̵��Է�: ");
					String id = input.readLine();
					System.out.println("�н����� �Է�: ");
					String passwd = input.readLine();
					System.out.println("�ּ� �Է�: ");
					String addr = input.readLine();
					System.out.println("��ȭ��ȣ �Է�: ");
					String phone = input.readLine();
					
					stmt = conn.createStatement();
					sql = "update member set id = '"+id+"', passwd = '"+passwd+"', addr = '"+addr+"', phone = '"+phone+"' where id = '"+session+"'";
					cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "�� ȸ���� �����Ǿ����ϴ�.");
					
				} catch (SQLException e) {
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
				
			}
			
			else if(protocol.equals("I") || protocol.equals("i")) {
				try {
					System.out.println("���̵� �Է�: ");
					String loginID = input.readLine();
					System.out.println("�н����� �Է�: ");
					String loginPw = input.readLine();
					try {
						Statement stmt = conn.createStatement();
						String sql = "select id from member where id = '"+loginID+"' and passwd = '" + loginPw + "'" ;
						ResultSet rs = stmt.executeQuery(sql);
						if(!rs.isBeforeFirst()) {
							System.out.println("���̵� ���� ���� �ʽ��ϴ�.");
						}
						if(rs!=null) {
							while(rs.next()) {
								System.out.println("�α��� ����");
								session = loginID;
							}
						}				
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
			
			else if(protocol.equals("O") || protocol.equals("o")) {
				session = null;
				System.out.println("�α׾ƿ�");
			}
		}
	}

}
