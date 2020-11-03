package test.main;

import test.tcp.TCPEchoClient;

public class ClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TCPEchoClient().clientTCP("localhost", 1005);
	}

}
