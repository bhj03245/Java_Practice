package chat.Server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyServerChat {
	Scanner sc = new Scanner(System.in);
	public MyServerChat()
	{
		ServerSocket chatServerSocket = null;
		int listenPort = 10000; //TCP port
		Socket clientSocket = null;
		OutputStream out = null;
		DataOutputStream dos = null;
		InputStream in = null;
		DataInputStream dis = null;
		String sendMsg = null;
		String recvMsg = null;
		try {
			System.out.println(clock() + "채팅 서버를 구동 중입니다....");
			Thread.sleep(1000);
			chatServerSocket = new ServerSocket(listenPort);
			System.out.println(clock() + "채팅 서버를 구동하였습니다");
			Thread.sleep(1000);
			System.out.println(clock() + "클라이언트의 접속을 기다리고 있습니다");
			clientSocket = chatServerSocket.accept();
			System.out.println(clock() + clientSocket.getInetAddress() + " 클라이언트가 접속하였습니다");
			out = clientSocket.getOutputStream();
			dos = new DataOutputStream(out);
			in = clientSocket.getInputStream();
			dis = new DataInputStream(in);
			System.out.println(clock() + "채팅이 시작되었습니다");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true)
		{
			try {
				System.out.print("서버(나) : ");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				if(sendMsg.equals("exit"))break; //내가 exit 입력시 종료 (상대한테 보내고 종료)
				recvMsg = dis.readUTF();
				if(recvMsg.equals("exit")) {
					System.out.println("클라이언트(상대) : 상대방이 채팅을 종료하였습니다");
					break; //상대가 exit 입력시 종료
				}
				System.out.println("클라이언트(상대) : " + recvMsg);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}	
		}
		System.out.println(clock() + "채팅서버를 종료합니다");
		try {
			dis.close(); // 받는 바이트 스트림 종료
			dos.close(); // 보내는 바이트 스트림 종료
			chatServerSocket.close(); //서버 소켓 종료
			//클라이언트와의 연결 소켓은 클라이언트가 닫으면 자동으로 닫힘
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
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
