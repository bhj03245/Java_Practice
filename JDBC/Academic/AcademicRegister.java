package kr.co.kh.obj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AcademicRegister {
	private String protocol;
	public static BufferedReader input;
	private Statement stmt;
	private static Connection conn;
	private String age1;
	private String name;
	private String hakbun1;
	private int age;
	private int hakbun;
	private int cnt;
	private String subject;
	private String part;
	private String sql;
	
	static {
		input = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public AcademicRegister(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �� ���ڴ�.) 
			//Class.forName("com.mysql.jdbc.Driver"); //1.�ε�(����=�ڹٿ��� ���� �����ͺ��̽��� �� ���ڴ�.) mysql
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{	
		conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass","dkdlxl");
		//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8", "root", "");
		return conn;
	}
	
	public String setInput() throws IOException{
		System.out.println("==========�޴�����===========");
		System.out.println("1.�л�");
		System.out.println("2.����");
		System.out.println("3.������");
		System.out.println("4.�����޴�");
		System.out.println("��ȣ�� �������ּ���.");
		
		protocol = input.readLine();
		
		return protocol;
	}
	
	public void setAge() throws IOException{
		System.out.println("����: ");
		age1 = input.readLine();
	}
	
	public void setName() throws IOException{
		System.out.println("�̸�: ");
		name = input.readLine();
	}
	
	public void setHakbun() throws IOException{
		System.out.println("�й�: ");
		hakbun1 = input.readLine();
	}
	
	public void setSubject() throws IOException{
		System.out.println("����: ");
		subject = input.readLine();
	}
	
	public void setPart() throws IOException{
		System.out.println("�μ�: ");
		part = input.readLine();
	}
	
	public String haksaProcess() throws IOException, SQLException{
		if(protocol.equals("1")) {
			setAge();
			setName();
			setHakbun();
			age = Integer.parseInt(age1);
			hakbun = Integer.parseInt(hakbun1);	
			
			stmt = conn.createStatement();
			sql = "insert into student(no,age,name,hakbun) values(student_no.nextval,"+age+",'"+name+"',"+hakbun+")";
			cnt = stmt.executeUpdate(sql);
			studentDisp();
		}
		else if(protocol.equals("2")) {
			setAge();
			setName();
			setSubject();	
			age = Integer.parseInt(age1);
		
			stmt = conn.createStatement();
			sql = "insert into professor(no,age,name,subject) values(professor_no.nextval,"+age+",'"+name+"','"+subject+"')";
			cnt = stmt.executeUpdate(sql);
			professorDisp();
		}
		else if(protocol.equals("3")) {
			setAge();
			setName();
			setPart();
			stmt = conn.createStatement();
			age = Integer.parseInt(age1);
			sql = "insert into manager(no,name,age,part) values(manager_no.nextval,'"+name+"',"+age+",'"+part+"')";
			cnt = stmt.executeUpdate(sql);
			managerDisp();
		}
		return protocol;
	}
	
	public void studentDisp() {
		System.out.println(cnt+"�� �л��� ��ϵǾ����ϴ�.");	
	}
	
	public void professorDisp() {
		System.out.println(cnt+"�� ������ ��ϵǾ����ϴ�. ");
	}
	
	public void managerDisp() {
		System.out.println(cnt+"�� �����ڰ� ��ϵǾ����ϴ�. ");
	}
	
	/* ���� �׽�Ʈ��
	public static void main(String[] args) {
		AcademicRegister register = new AcademicRegister();
		try {
			Connection conn = register.getConnection();
			register.setInput();
			register.haksaProcess();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	*/
	
}
