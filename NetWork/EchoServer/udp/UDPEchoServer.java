package test.udp;

import java.net.*;

public class UDPEchoServer {
	public void serverUDP(int port) {
		DatagramSocket dSock = null;
		
		try {
			dSock = new DatagramSocket(port);
			while(true) {
				System.out.println("====== 클라이언트 접속 대기 중 (port" + dSock.getPort() + ") ======");
				
				byte[] buffer = new byte[1024];
				DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
				dSock.receive(receive);
				String msg = new String(receive.getData(),0,receive.getLength());
				System.out.println("\tCLIENT > " + msg);
				
				DatagramPacket send = new DatagramPacket(
						receive.getData(),
						receive.getData().length,
						receive.getAddress(),
						receive.getPort());
						dSock.send(send);
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			dSock.close();
		}
	}
}
