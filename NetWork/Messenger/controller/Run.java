package messenger.controller;

import javax.swing.JOptionPane;

import messenger.model.Messenger;
import messenger.view.MyFrame;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int myPort = Integer.parseInt(JOptionPane.showInputDialog("����� ��Ʈ��ȣ�� �Է��ϼ���. "));
		int otherPort = Integer.parseInt(JOptionPane.showInputDialog("���� ��Ʈ��ȣ�� �Է��ϼ���. "));
		
		Messenger a = new Messenger(myPort, otherPort);
		MyFrame f = a.getMyFrame();
		f.process();
	}

}
