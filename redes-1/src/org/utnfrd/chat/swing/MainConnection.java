package org.utnfrd.chat.swing;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainConnection extends JFrame {

		
	public MainConnection()  {
				
		super();
		
		configurarVentana();
		inicializarComponentes();
	}

	private void configurarVentana() {
		
		this.setTitle("Main-Connection-Emulator");
		this.setSize(550, 100);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}		
		
	private void inicializarComponentes() {
		
		JButton createServer = new JButton();
		createServer.setText("Create Server");
		createServer.setBounds(20, 20, 150, 30);
		createServer.addActionListener(createServerActionListener());
		this.add(createServer);

		JButton createClient = new JButton();
		createClient.setText("Create Client");
		createClient.setBounds(190, 20, 150, 30);
		createClient.addActionListener(createClientActionListener());
		this.add(createClient);		


		JButton createChatConsoleLog = new JButton();
		createChatConsoleLog.setText("Chat Console Log");
		createChatConsoleLog.setBounds(350, 20, 170, 30);
		createChatConsoleLog.addActionListener(createChatConsoleLogActionListener());
		this.add(createChatConsoleLog);				
		
	}

	private ActionListener createServerActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
							
				ServerEmulator serverEmulator = ServerEmulator.getInstance();
				
				serverEmulator.setLocation(0, 0);
				serverEmulator.setVisible(true);				
				
			}
		};
	}
		
	private ActionListener createClientActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				ChatEmulator chatClient = new ChatEmulator("127.0.0.1" , 6789, "127.0.0.1" , 6789, "Jonatan", "Ana");
				
				chatClient.setLocation(750, 250);
				
				chatClient.setVisible(true);				
			}
		};
	}
	
	private ActionListener createChatConsoleLogActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				ChatConsoleLogEmulator chatConsoleLogEmulator = ChatConsoleLogEmulator.getInstance();				
				
				chatConsoleLogEmulator.setLocation(0, 510);
				chatConsoleLogEmulator.setVisible(true);
			}
		};
	}	
	
	public static void main(String[] args) {

		MainConnection mainConnection = new MainConnection();
		
		mainConnection.setLocation(800, 0);
		mainConnection.setVisible(true);		
	}	

}