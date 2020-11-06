package multicast.chat.client;

import java.io.*;
import java.net.*;

public class ClientBackground {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ClientGUI gui;
	private String msg;
	private String nickName;
	
	public void setGui(ClientGUI gui) {
		this.gui = gui;
	}
	
	public void connect() {
		try {
			socket = new Socket("192.168.20.70", 7777);
			System.out.println("서버에 연결됨");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			out.writeUTF(nickName);
			System.out.println("Client : 닉네임 전송 완료");
			
			while(in!=null) {
				msg = in.readUTF();
				gui.appendMsg(msg);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String msg) {
		try {
			out.writeUTF(msg);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setNickname(String nickName) {
		this.nickName = nickName;
	}
}
