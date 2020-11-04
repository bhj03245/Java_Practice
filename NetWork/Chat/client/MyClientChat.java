package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyClientChat {
	Scanner sc = new Scanner(System.in);
	public MyClientChat()
	{
		String ServerIP = "192.168.20.70"; // ���� IP �ּ�
		int ServerPort = 10000; //���� ��Ʈ
		Socket socket = null; // ������ ����� ���� ���۷���
		OutputStream out = null;
		DataOutputStream dos = null;
		InputStream in = null;
		DataInputStream dis = null;
		String sendMsg = null;
		String recvMsg = null;
		
		try {
			System.out.println(clock() + "������ ���� �� �Դϴ�");
			Thread.sleep(1000);
			socket = new Socket(ServerIP,ServerPort);
			System.out.println(clock() + socket.getInetAddress() + "ä�ü����� �����Ͽ����ϴ�");
			out = socket.getOutputStream();
			dos = new DataOutputStream(out);
			in = socket.getInputStream();
			dis = new DataInputStream(in);
			Thread.sleep(1000);
			System.out.println(clock() + "�������� ä���� �����մϴ�");
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
				recvMsg = dis.readUTF();
				if(recvMsg.equals("exit"))
				{
					System.out.println("����(���) : ��밡 ä���� �����Ͽ����ϴ�");
					break;
				}
				System.out.println("����(���) : " + recvMsg);
				
				System.out.print("Ŭ���̾�Ʈ(��) : ");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				if(sendMsg.equals("exit"))break;
				
								
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(clock() + "ä�ü����� �����մϴ�");
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
