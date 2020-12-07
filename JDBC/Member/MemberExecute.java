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
				//Class.forName("oracle.jdbc.driver.OracleDriver"); //1.로드(적재=자바에게 내가 데이터베이스를 뭘 쓰겠다.) 
				Class.forName("com.mysql.jdbc.Driver"); //1.로드(적재=자바에게 내가 데이터베이스를 뭘 쓰겠다.) mysql
				//conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass","dkdlxl");
				//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8", "root", "");
				conn = DriverManager.getConnection("jdbc:mysql://underdogb.cafe24.com:3306/underdogb?characterEncoding=utf8", "underdogb", "khacademy1!");
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
				System.out.println("회원 가입");
				if(session == null) {
					try {
						System.out.println("아이디입력: ");
						String id = input.readLine();
						System.out.println("패스워드 입력: ");
						String pw = input.readLine();
						System.out.println("주소 입력: ");
						String addr = input.readLine();
						System.out.println("전화번호 입력: ");
						String tel = input.readLine();
						try {
							sql = "insert into member(id,pw,addr,tel) values(?,?,?,?)";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, id);
							pstmt.setString(2, pw);
							pstmt.setString(3, addr);
							pstmt.setString(4, tel);
							cnt = pstmt.executeUpdate();
							System.out.println(cnt + "건 회원이 입력되었습니다.");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println();
				}
				else {
					System.out.println("로그인 중에는 회원가입할 수 업습니다.");
					System.out.println();
				}
			}
			
			else if(protocol.equals("L") || protocol.equals("l")) {
				System.out.println("회원 목록 출력");
				try {
					sql = "SELECT id,pw,addr,tel from member";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					System.out.println("회원아이디 \t 회원패스워드 \t 회원주소 \t 회원전화번호");
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
				System.out.println("회원 검색");
				System.out.println("검색할 아이디를 입력해주세요.");
				try {
					String searchID = input.readLine();
					sql = "select * from member where id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, searchID);
					rs = pstmt.executeQuery();
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
				System.out.println();
			
			}
			
			else if(protocol.equals("D") || protocol.equals("d")) {
				if(session == null) {
					System.out.println("로그인 해주세요.");
					System.out.println();
				}
				else{
					System.out.println("회원 탈퇴");
					System.out.println("정말 탈퇴하시겠습니까? y/n");
					try {
						String result = input.readLine();
						if(result.equals("y")||result.equals("Y")) {
							sql = "delete from member where id = ?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, session);
							cnt = pstmt.executeUpdate();	
							if(cnt>=1) {
								System.out.println("회원 탈퇴되었습니다.");
							}
							session = null;
						}
						else if(result.equals("n")|| result.equals("N")){
							continue;
						}
						else {
							System.out.println("y와n중 하나만 입력하세요. ");
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
					System.out.println("로그인 해주세요.");
					System.out.println();
				}
				else{
					System.out.println("회원 정보 수정");
					try {
						sql = "select * from member where id = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, session);
						rs = pstmt.executeQuery();
							
						if(rs!=null) {
							while(rs.next()) {
								System.out.println("회원아이디 \t 회원패스워드 \t 회원주소 \t 회원전화번호");
								System.out.print(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\n");
							}
						}
						
						System.out.println("정말 수정하시겠습니까? y/n ");
						String result = input.readLine();
						if(result.equals("y")||result.equals("Y")) {
							System.out.println("아이디입력: ");
							String id = input.readLine();
							System.out.println("패스워드 입력: ");
							String pw = input.readLine();
							System.out.println("주소 입력: ");
							String addr = input.readLine();
							System.out.println("전화번호 입력: ");
							String tel = input.readLine();
							
							sql = "update member set id = ?, pw = ?, addr = ?, tel = ? where id = ?";
							pstmt.setString(1, id);
							pstmt.setString(2, pw);
							pstmt.setString(3, tel);
							pstmt.setString(4, tel);
							pstmt.setString(5, session);
							pstmt = conn.prepareStatement(sql);
							cnt = pstmt.executeUpdate();
							System.out.println(cnt + "건 회원이 수정되었습니다. 다시 로그인 해주세요.");
							session = id;
						}
						else if(result.equals("n")|| result.equals("N")){
							continue;
						}
						else {
							System.out.println("y와n중 하나만 입력하세요. ");
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
					System.out.println("현재 로그인 중입니다.");
					continue;
				}
				
				try {
					System.out.println("아이디 입력: ");
					String loginID = input.readLine();
					System.out.println("패스워드 입력: ");
					String loginPw = input.readLine();
					try {
						sql = "select id from member where id = ? and pw  = ?" ;
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, loginID);
						pstmt.setString(2, loginPw);
						rs = pstmt.executeQuery();
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
					System.out.println("로그인 해주세요.");
					System.out.println();
				}
				else{
					session = null;
					System.out.println("로그아웃");
					System.out.println();
				}
			}
		}
	}

}
