package multicast.chat.client;

import javax.swing.JOptionPane;

public class ClientRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nickName = JOptionPane.showInputDialog("�г����� �Է��ϼ���.");
		new ClientGUI(nickName);
	}

}
