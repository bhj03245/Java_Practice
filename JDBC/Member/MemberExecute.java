package kr.co.kh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;
		while(true) {
			try {
				//Class.forName("oracle.jdbc.driver.OracleDriver"); //1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �� ���ڴ�.) 
				Class.forName("com.mysql.jdbc.Driver"); //1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �� ���ڴ�.) mysql
				//conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass","dkdlxl");
				//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8", "root", "");
				conn = DriverManager.getConnection("jdbc:mysql://underdogb.cafe24.com:3306/underdogb?characterEncoding=utf8", "underdogb", "khacademy1!");
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
				System.out.println("ȸ�� ����");
				if(session == null) {
					try {
						System.out.println("���̵��Է�: ");
						String id = input.readLine();
						System.out.println("�н����� �Է�: ");
						String pw = input.readLine();
						System.out.println("�ּ� �Է�: ");
						String addr = input.readLine();
						System.out.println("��ȭ��ȣ �Է�: ");
						String tel = input.readLine();
						try {
							sql = "insert into member(id,pw,addr,tel) values(?,?,?,?)";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, id);
							pstmt.setString(2, pw);
							pstmt.setString(3, addr);
							pstmt.setString(4, tel);
							cnt = pstmt.executeUpdate();
							System.out.println(cnt + "�� ȸ���� �ԷµǾ����ϴ�.");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println();
				}
				else {
					System.out.println("�α��� �߿��� ȸ�������� �� �����ϴ�.");
					System.out.println();
				}
			}
			
			else if(protocol.equals("L") || protocol.equals("l")) {
				System.out.println("ȸ�� ��� ���");
				try {
					sql = "SELECT id,pw,addr,tel from member";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					System.out.println("ȸ�����̵� \t ȸ���н����� \t ȸ���ּ� \t ȸ����ȭ��ȣ");
					while(rs.next()) {
						String id = rs.getString("id");
						String pw = rs.getString("pw");
						String addr = rs.getString("addr");
						String tel = rs.getString("tel");
						System.out.print(id+"\t"+pw+"\t"+addr+"\t"+tel+"\n");
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
					sql = "select * from member where id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, searchID);
					rs = pstmt.executeQuery();
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
				System.out.println();
			
			}
			
			else if(protocol.equals("D") || protocol.equals("d")) {
				if(session == null) {
					System.out.println("�α��� ���ּ���.");
					System.out.println();
				}
				else{
					System.out.println("ȸ�� Ż��");
					System.out.println("���� Ż���Ͻðڽ��ϱ�? y/n");
					try {
						String result = input.readLine();
						if(result.equals("y")||result.equals("Y")) {
							sql = "delete from member where id = ?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, session);
							cnt = pstmt.executeUpdate();	
							if(cnt>=1) {
								System.out.println("ȸ�� Ż��Ǿ����ϴ�.");
							}
							session = null;
						}
						else if(result.equals("n")|| result.equals("N")){
							continue;
						}
						else {
							System.out.println("y��n�� �ϳ��� �Է��ϼ���. ");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println();
				}
			}
			
			else if(protocol.equals("E") || protocol.equals("e")) {
				if(session == null) {
					System.out.println("�α��� ���ּ���.");
					System.out.println();
				}
				else{
					System.out.println("ȸ�� ���� ����");
					try {
						sql = "select * from member where id = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, session);
						rs = pstmt.executeQuery();
							
						if(rs!=null) {
							while(rs.next()) {
								System.out.println("ȸ�����̵� \t ȸ���н����� \t ȸ���ּ� \t ȸ����ȭ��ȣ");
								System.out.print(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\n");
							}
						}
						
						System.out.println("���� �����Ͻðڽ��ϱ�? y/n ");
						String result = input.readLine();
						if(result.equals("y")||result.equals("Y")) {
							System.out.println("���̵��Է�: ");
							String id = input.readLine();
							System.out.println("�н����� �Է�: ");
							String pw = input.readLine();
							System.out.println("�ּ� �Է�: ");
							String addr = input.readLine();
							System.out.println("��ȭ��ȣ �Է�: ");
							String tel = input.readLine();
							
							sql = "update member set id = ?, pw = ?, addr = ?, tel = ? where id = ?";
							pstmt.setString(1, id);
							pstmt.setString(2, pw);
							pstmt.setString(3, tel);
							pstmt.setString(4, tel);
							pstmt.setString(5, session);
							pstmt = conn.prepareStatement(sql);
							cnt = pstmt.executeUpdate();
							System.out.println(cnt + "�� ȸ���� �����Ǿ����ϴ�. �ٽ� �α��� ���ּ���.");
							session = id;
						}
						else if(result.equals("n")|| result.equals("N")){
							continue;
						}
						else {
							System.out.println("y��n�� �ϳ��� �Է��ϼ���. ");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} catch(IOException e) {
						e.printStackTrace();
					}
					System.out.println();
				}
			}
			
			else if(protocol.equals("I") || protocol.equals("i")) {
				if(session != null){
					System.out.println("���� �α��� ���Դϴ�.");
					continue;
				}
				
				try {
					System.out.println("���̵� �Է�: ");
					String loginID = input.readLine();
					System.out.println("�н����� �Է�: ");
					String loginPw = input.readLine();
					try {
						sql = "select id from member where id = ? and pw  = ?" ;
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, loginID);
						pstmt.setString(2, loginPw);
						rs = pstmt.executeQuery();
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
					}finally {
						try {
							pstmt.close();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println();
			}
			
			else if(protocol.equals("O") || protocol.equals("o")) {
				if(session == null) {
					System.out.println("�α��� ���ּ���.");
					System.out.println();
				}
				else{
					session = null;
					System.out.println("�α׾ƿ�");
					System.out.println();
				}
			}
		}
	}

}
