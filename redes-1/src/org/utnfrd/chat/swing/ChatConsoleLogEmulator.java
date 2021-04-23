package org.utnfrd.chat.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatConsoleLogEmulator extends JFrame {

	static ChatConsoleLogEmulator chatConsoleLogEmulator;
	
	// ConnectionTCPServer connectionTCPServer;	
	
	JTextField portJTextField;
	
	private JTextArea console;
		
	private JScrollPane scroll;
	
	
	public static ChatConsoleLogEmulator getInstance() {
		
		if(chatConsoleLogEmulator==null) 
			chatConsoleLogEmulator = new ChatConsoleLogEmulator();
		
		return chatConsoleLogEmulator;		
	}	
	
	private ChatConsoleLogEmulator()  {
		
		super();
		
		//connectionTCPServer = new ConnectionTCPServer();
		
		configurarVentana();
		inicializarComponentes();
	}

	private void configurarVentana() {
		
		this.setTitle("Chat-Console-Log-Emulator");
		this.setSize(650, 350);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}		
		
	private void inicializarComponentes() {
						
		JLabel serverConsoleLogLabel = new JLabel();
		serverConsoleLogLabel.setText("Chat Console Log");		
		serverConsoleLogLabel.setBounds(20, 20, 150, 30);
		this.add(serverConsoleLogLabel);		
		
		console = new JTextArea();		
		
		scroll = new JScrollPane(console);
		scroll.setBounds(20, 60, 600, 230);
		this.add(scroll);
		
		JButton clearJButton = new JButton();
		clearJButton.setText("Clear");
		clearJButton.setBounds(470, 20, 150, 30);
		clearJButton.addActionListener(clearConsoleActionListener());
		this.add(clearJButton);		
				
	}
	
	private ActionListener clearConsoleActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				console.setText("");
			}
		};
	}	
	
	public void log(String logMessage) {
				
		this.console.setText(this.console.getText() + "\n" + logMessage);
		
	}
		
}
