package test.tcp;

import java.io.*;
import java.net.*;

public class TCPEchoServer {
	public void tcpServer(int port) {
		ServerSocket serverSock = null;
		Socket sock = null;
		InetAddress inetAddr = null;
		
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;
		PrintWriter wr = null;
		
		try {
			serverSock = new ServerSocket(port);
			while(true) {
				System.out.println("======Ŭ���̾�Ʈ ���� ��� �� " + "(port" + serverSock.getLocalPort() + ")======");
				sock = serverSock.accept();
				
				inetAddr = serverSock.getInetAddress();
				System.out.println("Ŭ���̾�Ʈ (" + inetAddr.getHostAddress() + ") ����");
				
				in = sock.getInputStream();
				out = sock.getOutputStream();
				br = new BufferedReader(new InputStreamReader(in));
				wr = new PrintWriter(new OutputStreamWriter(out));
				String msg = null;
				
				while((msg = br.readLine()) != null) {
					System.out.println("\tCLIENT> " + msg);
					wr.println(msg);
					wr.flush();
				}
			}
		}catch(IOException ie) {
			System.out.println(ie);
		}finally {
			try {
				br.close();
				wr.close();
				in.close();
				out.close();
				sock.close();
				serverSock.close();
				System.out.println("����");
			}catch(IOException ie) {
				System.out.println(ie);
			}
		}
	}
}
