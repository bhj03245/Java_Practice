package multicast.chat.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerBackground {
	private ServerSocket serverSocket;
	private Socket socket;
	private ServerGUI gui;
	private String msg;
	private int count;
	private Map<String, DataOutputStream>clientMap = new HashMap<String, DataOutputStream>();
	
	public void setGui(ServerGUI gui) {
		this.gui = gui;
	}
	
	public void setting() {
		try {
			Collections.synchronizedMap(clientMap);
			serverSocket = new ServerSocket(7000);
			while(true) {
				System.out.println("������ ��� ��...");
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress());
				
				Receiver receiver = new Receiver(socket);
				receiver.start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addClient(String nick, DataOutputStream out)throws IOException{
		String message = nick + "���� �����ϼ̽��ϴ�. \n";
		sendMessage(message);
		gui.appendMsg(message);
		clientMap.put(nick, out);
		System.out.println(">>> ���� ������ : " + (++count));
	}
	
	public void removeClient(String nick) {
		String message = nick + "���� �����̽��ϴ�. \n";
		sendMessage(message);
		gui.appendMsg(message);
		clientMap.remove(nick);
		System.out.println(">>> ���� ������ : "+(++count));
	}
	
	public void sendMessage(String msg) {
		Iterator<String> iterator = clientMap.keySet().iterator();
		String key = "";
		
		while(iterator.hasNext()) {
			key = iterator.next();
			try {
				clientMap.get(key).writeUTF(msg);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Receiver extends Thread{
		private DataInputStream in;
		private DataOutputStream out;
		private String nick;
		
		public Receiver(Socket socket) {
			try {
				out = new DataOutputStream(socket.getOutputStream());
				in= new DataInputStream(socket.getInputStream());
				nick = in.readUTF();
				addClient(nick,out);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override 
		public void run(){
			try {
				while(in != null) {
					msg = in.readUTF();
					sendMessage(msg);
					gui.appendMsg(msg);
				}
			}catch(Exception e) {
				removeClient(nick);
			}
		}
	}
}
