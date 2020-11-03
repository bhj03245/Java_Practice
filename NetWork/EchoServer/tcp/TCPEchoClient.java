package test.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPEchoClient {
	public void clientTCP(String ip, int port) {
		Socket sock = null;
		
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;
		PrintWriter wr = null;
		BufferedReader stdin = null;
		
		try {
			sock = new Socket(ip,port);
			System.out.println("접속 성공 !!");
			
			in = sock.getInputStream();
			out = sock.getOutputStream();
			br = new BufferedReader(new InputStreamReader(in));
			wr = new PrintWriter(new OutputStreamWriter(out));
			stdin = new BufferedReader(new InputStreamReader(System.in));
			String msg = null;
			String echo = null;
			
			do {
				System.out.print("\tInsert Msg > ");
				msg = stdin.readLine();
				
				if(msg.equals("exit")) {
					sock.close();
					break;
				}
				
				wr.println(msg);
				wr.flush();
				echo = br.readLine();
				System.out.println("\tSERVER> " + echo);
			}while(msg != null);
		}catch(IOException ie) {
			System.out.println(ie);
		}finally {
			try {
			br.close();
			wr.close();
			in.close();
			out.close();
			sock.close();
			stdin.close();
			System.out.println("종료");
		}catch(IOException ie) {
			System.out.println(ie);
		}
		}
	}
}
