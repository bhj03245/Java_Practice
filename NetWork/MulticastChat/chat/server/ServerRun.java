package multicast.chat.server;

import java.io.IOException;

public class ServerRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new ServerGUI();
		}catch(IOException e) {
			System.err.println(e.getMessage() + "\n서버 프로그램 실행 실패!!");
		}
	}

}
