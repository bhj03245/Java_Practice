package test.main;

import test.tcp.TCPEchoServer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TCPEchoServer().tcpServer(1005);
	}

}
