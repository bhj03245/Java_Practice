package multicast.chat.client;

import javax.swing.JOptionPane;

public class ClientRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");
		new ClientGUI(nickName);
	}

}
