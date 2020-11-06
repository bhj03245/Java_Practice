package multicast.chat.server;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;


public class ServerGUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 20000000L;
	private JTextArea jta = new JTextArea(40, 25);
	private JTextField jtf = new JTextField(25);
	private ServerBackground server = new ServerBackground();
	
	public ServerGUI() throws IOException{
		setBounds(800,100,400,600);
		setTitle("서버 부분");
		
		jta.setEditable(false);
		jta.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		jta.setBackground(new Color(255, 190, 190));
		
		add(jta, BorderLayout.CENTER);
		add(jtf, BorderLayout.SOUTH);
		jtf.addActionListener(this);	
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		server.setGui(this);
		server.setting();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = "운영자 : " + jtf.getText() + "\n";
		System.out.println(msg);
		server.sendMessage(msg);
		appendMsg(msg);
		jtf.setText("");
	}
	
	public void appendMsg(String msg) {
		jta.append(msg);
	}
}
