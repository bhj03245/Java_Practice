package function.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FunctionServer {
	Scanner sc = new Scanner(System.in);
	public FunctionServer()
	{
		ServerSocket chatServerSocket = null;
		int listenPort = 10000; //TCP port
		Socket clientSocket = null;
		OutputStream out = null;
		DataOutputStream dos = null;
		InputStream in = null;
		DataInputStream dis = null;
		int num1, num2, sum, min, mul;
		String op = null;
		String sendMsg = null;
		String recvMsg = null;
		
		try {
			System.out.println(clock() + "���� ������ ���� ���Դϴ�....");
			Thread.sleep(1000);
			chatServerSocket = new ServerSocket(listenPort);
			System.out.println(clock() + "���� ������ �����Ͽ����ϴ�");
			Thread.sleep(1000);
			System.out.println(clock() + "Ŭ���̾�Ʈ�� ������ ��ٸ��� �ֽ��ϴ�");
			clientSocket = chatServerSocket.accept();
			System.out.println(clock() + clientSocket.getInetAddress() + " Ŭ���̾�Ʈ�� �����Ͽ����ϴ�");
			out = clientSocket.getOutputStream();
			dos = new DataOutputStream(out);
			in = clientSocket.getInputStream();
			dis = new DataInputStream(in);
			System.out.println(clock() + "�����۾��� ���۵Ǿ����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true)
		{
			try {
				recvMsg = dis.readUTF();
				if(recvMsg.contains("exit")) {
					System.out.println("Ŭ���̾�Ʈ(���) : ������ ������ �����Ͽ����ϴ�");
					break; //��밡 exit �Է½� ����
				}
				if(recvMsg.contains("+")) {
					String []tokens = recvMsg.split("\\+");
					num1 = Integer.parseInt(tokens[0]);
					num2 = Integer.parseInt(tokens[1]);
					sum = num1 + num2;
					sendMsg = Integer.toString(sum);
				}
				else if(recvMsg.contains("-")) {
					String []tokens = recvMsg.split("\\-");
					num1 = Integer.parseInt(tokens[0]);
					num2 = Integer.parseInt(tokens[1]);
					min = num1 - num2;
					sendMsg = Integer.toString(min);
				}
				else if(recvMsg.contains("*")) {
					String []tokens = recvMsg.split("\\*");
					num1 = Integer.parseInt(tokens[0]);
					num2 = Integer.parseInt(tokens[1]);
					mul = num1 * num2;
					sendMsg = Integer.toString(mul);
				}
				dos.writeUTF(sendMsg);
				if(sendMsg.contains("exit"))break; //���� exit �Է½� ���� (������� ������ ����)
			} 
			catch (IOException e) {
				e.printStackTrace();
			}	
		}
		System.out.println(clock() + "ä�ü����� �����մϴ�");
		try {
			dis.close(); // �޴� ����Ʈ ��Ʈ�� ����
			dos.close(); // ������ ����Ʈ ��Ʈ�� ����
			chatServerSocket.close(); //���� ���� ����
			//Ŭ���̾�Ʈ���� ���� ������ Ŭ���̾�Ʈ�� ������ �ڵ����� ����
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
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
