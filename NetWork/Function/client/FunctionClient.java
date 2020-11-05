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
		String ServerIP = "***"; // 서버 IP 주소
		int ServerPort = 10000; //서버 포트
		Socket socket = null; // 서버와 연결될 소켓 레퍼런스
		OutputStream out = null;
		DataOutputStream dos = null;
		InputStream in = null;
		DataInputStream dis = null;
		int num1, num2;
		String op = null;
		String sendMsg = null;
		String recvMsg = null;
		
		try {
			System.out.println(clock() + "서버에 연결 중 입니다");
			Thread.sleep(1000);
			socket = new Socket(ServerIP,ServerPort);
			System.out.println(clock() + socket.getInetAddress() + "연산서버에 접속하였습니다");
			out = socket.getOutputStream();
			dos = new DataOutputStream(out);
			in = socket.getInputStream();
			dis = new DataInputStream(in);
			Thread.sleep(1000);
			System.out.println(clock() + "서버와 연산작업을 시작합니다");
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
				System.out.println("서버에게 연산을 의뢰할 수식을 입력하세요.");
				System.out.println("연산을 입력할 때 exit을 입력할 시 종료");
				System.out.print("숫자 1 입력 : ");
				num1 = sc.nextInt();
				System.out.print("숫자 2 입력 : ");
				num2 = sc.nextInt();
				System.out.print("연산자 입력 : ");
				op = sc.next();
				
				sendMsg = Integer.toString(num1) + op + Integer.toString(num2);
				dos.writeUTF(sendMsg);
				if(sendMsg.contains("exit"))break;
				
				recvMsg = dis.readUTF();
				System.out.println("계산 결과 : " + recvMsg);				
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(clock() + "연산서버를 종료합니다");
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
