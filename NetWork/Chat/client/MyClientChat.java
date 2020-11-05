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
		String ServerIP = "***"; // 서버 IP 주소
		int ServerPort = 10000; //서버 포트
		Socket socket = null; // 서버와 연결될 소켓 레퍼런스
		OutputStream out = null;
		DataOutputStream dos = null;
		InputStream in = null;
		DataInputStream dis = null;
		String sendMsg = null;
		String recvMsg = null;
		
		try {
			System.out.println(clock() + "서버에 연결 중 입니다");
			Thread.sleep(1000);
			socket = new Socket(ServerIP,ServerPort);
			System.out.println(clock() + socket.getInetAddress() + "채팅서버에 접속하였습니다");
			out = socket.getOutputStream();
			dos = new DataOutputStream(out);
			in = socket.getInputStream();
			dis = new DataInputStream(in);
			Thread.sleep(1000);
			System.out.println(clock() + "서버와의 채팅을 시작합니다");
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
					System.out.println("서버(상대) : 상대가 채팅을 종료하였습니다");
					break;
				}
				System.out.println("서버(상대) : " + recvMsg);
				
				System.out.print("클라이언트(나) : ");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				if(sendMsg.equals("exit"))break;
				
								
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(clock() + "채팅서버를 종료합니다");
		try {
			dis.close(); // 받는 바이트 스트림 종료
			dos.close(); // 보내는 바이트 스트림 종료
			in.close(); //소켓 스트림 종료
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	String clock()
	{
		Date dtime = new Date(); //시간객체 생성
		String time = new SimpleDateFormat("[HH:mm:ss]").format(dtime); //시간 값을 포맷하여 문자열로 저장
		return time; // 포맷된 문자열 리턴
	}
}
