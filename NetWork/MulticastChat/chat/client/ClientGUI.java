package multicast.chat.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ClientGUI extends JFrame implements ActionListener{
	private JTextArea jta = new JTextArea(40, 25);
	private JTextField jtf = new JTextField(25);
	private ClientBackground client = new ClientBackground();
	private static String nickName;
	
	public ClientGUI(String nickName) {
		ClientGUI.nickName = nickName;
		
		setBounds(800,100,400,600);
		jta.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		setTitle("클라이언트 부분");
		
		
		
		add(jta);
		add(jta, BorderLayout.CENTER);
		add(jtf, BorderLayout.SOUTH);
	
		jtf.addActionListener(this);
		jta.setEditable(false);
		jta.setBackground(new Color(230, 255, 230));
		
		JScrollPane scrollPane = new JScrollPane(jta);
		//scrollPane.setViewportView(jta);
		add(scrollPane);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		client.setGui(this);
		client.setNickname(nickName);
		client.connect();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = nickName + ":" + jtf.getText() + "\n";
		client.sendMessage(msg);
		jtf.setText("");
	}
	
	public void appendMsg(String msg) {
		jta.append(msg);
	}
}
