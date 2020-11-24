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
				Class.forName("oracle.jdbc.driver.OracleDriver"); //1.로드(적재=자바에게 내가 데이터베이스를 뭘 쓰겠다.) 
				//Class.forName("com.mysql.jdbc.Driver"); //1.로드(적재=자바에게 내가 데이터베이스를 뭘 쓰겠다.) mysql
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass","dkdlxl");
				//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8", "root", "");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("회원 관리");
			System.out.println("R: 회원가입 L: 회원 목록 S: ID찾기 D: 회원 탈퇴 E: 회원 수정 I:로그인 O:로그아웃");
			try {
				protocol = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(protocol.equals("R")|| protocol.equals("r")) {
				try {
					System.out.println("아이디입력: ");
					String id = input.readLine();
					System.out.println("패스워드 입력: ");
					String passwd = input.readLine();
					System.out.println("주소 입력: ");
					String addr = input.readLine();
					System.out.println("전화번호 입력: ");
					String phone = input.readLine();
					try {
						Statement stmt = conn.createStatement();
						String sql = "insert into member(id,passwd,addr,phone) values('"+id+"','"+passwd+"','"+addr+"','"+phone+"')";
						cnt = stmt.executeUpdate(sql);
						System.out.println(cnt + "건 회원이 입력되었습니다.");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println();
			}
			
			else if(protocol.equals("L") || protocol.equals("l")) {
				System.out.println("회원 목록 출력");
				try {
					Statement stmt = conn.createStatement();
					String sql = "SELECT id,passwd,addr,phone from member";
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println("회원아이디 \t 회원패스워드 \t 회원주소 \t 회원전화번호");
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
				System.out.println("회원 검색");
				System.out.println("검색할 아이디를 입력해주세요.");
				try {
					String searchID = input.readLine();
					Statement stmt = conn.createStatement();
					String sql = "select * from member where id = '"+searchID+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(!rs.isBeforeFirst()) {
						System.out.println("찾는 아이디가 존재 하지 않습니다.");
					}
					if(rs!=null) {
						while(rs.next()) {
							System.out.println("회원아이디 \t 회원패스워드 \t 회원주소 \t 회원전화번호");
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
				System.out.println("회원 탈퇴");
				try {
					Statement stmt = conn.createStatement();
					String sql = "delete from member where id = '"+session+"'";
					cnt = stmt.executeUpdate(sql);
					if(cnt>=1) {
						System.out.println("회원 탈퇴되었습니다.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			else if(protocol.equals("E") || protocol.equals("e")) {
				System.out.println("회원 정보 수정");
				try {
					Statement stmt = conn.createStatement();
					String sql = "select * from member where id = '"+session+"'";
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs!=null) {
						while(rs.next()) {
							System.out.println("회원아이디 \t 회원패스워드 \t 회원주소 \t 회원전화번호");
							System.out.print(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\n");
						}
					}
					
					System.out.println("아이디입력: ");
					String id = input.readLine();
					System.out.println("패스워드 입력: ");
					String passwd = input.readLine();
					System.out.println("주소 입력: ");
					String addr = input.readLine();
					System.out.println("전화번호 입력: ");
					String phone = input.readLine();
					
					stmt = conn.createStatement();
					sql = "update member set id = '"+id+"', passwd = '"+passwd+"', addr = '"+addr+"', phone = '"+phone+"' where id = '"+session+"'";
					cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "건 회원이 수정되었습니다.");
					
				} catch (SQLException e) {
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
				
			}
			
			else if(protocol.equals("I") || protocol.equals("i")) {
				try {
					System.out.println("아이디 입력: ");
					String loginID = input.readLine();
					System.out.println("패스워드 입력: ");
					String loginPw = input.readLine();
					try {
						Statement stmt = conn.createStatement();
						String sql = "select id from member where id = '"+loginID+"' and passwd = '" + loginPw + "'" ;
						ResultSet rs = stmt.executeQuery(sql);
						if(!rs.isBeforeFirst()) {
							System.out.println("아이디가 존재 하지 않습니다.");
						}
						if(rs!=null) {
							while(rs.next()) {
								System.out.println("로그인 성공");
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
				System.out.println("로그아웃");
			}
		}
	}

}
