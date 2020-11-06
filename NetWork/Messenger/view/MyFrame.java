package messenger.view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener{
	private DatagramSocket socket;
	private DatagramPacket packet;
	private JTextField textField;
	private JTextArea textArea;
	private InetAddress  address;
	private int otherPort;
	
	public MyFrame(InetAddress address, int otherPort,DatagramSocket socket) {
		this.address = address;
		this.otherPort = otherPort;
		this.socket = socket;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField(30);
		textField.addActionListener(this);
		
		textArea = new JTextArea(10, 30);
		textArea.setEditable(false);
		
		this.add(textField, BorderLayout.PAGE_END);
		this.add(textArea, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String str = textField.getText();
		byte[] buffer = str.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, address, otherPort);
		try {
			socket.send(sendPacket);
		}catch(IOException e) {
			e.printStackTrace();
		}
		textArea.append("SENT : " + str + "\n");
		textField.selectAll();
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	public void process() {
		while(true) {
			byte[] buf = new byte[256];
			packet = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);
				textArea.append("RECEIVE : " + new String(buf) + "\n");
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
