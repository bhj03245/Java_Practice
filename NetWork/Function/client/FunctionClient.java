package function.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FunctionClient {
	Scanner sc = new Scanner(System.in);
	public FunctionClient()
	{
		String ServerIP = "192.168.20.70"; // ���� IP �ּ�
		int ServerPort = 10000; //���� ��Ʈ
		Socket socket = null; // ������ ����� ���� ���۷���
		OutputStream out = null;
		DataOutputStream dos = null;
		InputStream in = null;
		DataInputStream dis = null;
		int num1, num2;
		String op = null;
		String sendMsg = null;
		String recvMsg = null;
		
		try {
			System.out.println(clock() + "������ ���� �� �Դϴ�");
			Thread.sleep(1000);
			socket = new Socket(ServerIP,ServerPort);
			System.out.println(clock() + socket.getInetAddress() + "���꼭���� �����Ͽ����ϴ�");
			out = socket.getOutputStream();
			dos = new DataOutputStream(out);
			in = socket.getInputStream();
			dis = new DataInputStream(in);
			Thread.sleep(1000);
			System.out.println(clock() + "������ �����۾��� �����մϴ�");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true)
		{
			try {
				System.out.println("�������� ������ �Ƿ��� ������ �Է��ϼ���.");
				System.out.println("������ �Է��� �� exit�� �Է��� �� ����");
				System.out.print("���� 1 �Է� : ");
				num1 = sc.nextInt();
				System.out.print("���� 2 �Է� : ");
				num2 = sc.nextInt();
				System.out.print("������ �Է� : ");
				op = sc.next();
				
				sendMsg = Integer.toString(num1) + op + Integer.toString(num2);
				dos.writeUTF(sendMsg);
				if(sendMsg.contains("exit"))break;
				
				recvMsg = dis.readUTF();
				System.out.println("��� ��� : " + recvMsg);				
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(clock() + "���꼭���� �����մϴ�");
		try {
			dis.close(); // �޴� ����Ʈ ��Ʈ�� ����
			dos.close(); // ������ ����Ʈ ��Ʈ�� ����
			in.close(); //���� ��Ʈ�� ����
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	String clock()
	{
		Date dtime = new Date(); //�ð���ü ����
		String time = new SimpleDateFormat("[HH:mm:ss]").format(dtime); //�ð� ���� �����Ͽ� ���ڿ��� ����
		return time; // ���˵� ���ڿ� ����
	}
}
