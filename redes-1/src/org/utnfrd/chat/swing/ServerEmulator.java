package org.utnfrd.chat.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.utnfrd.chat.connection.impl.ConnectionTCPServer;

public class ServerEmulator extends JFrame {

	static ServerEmulator serverEmulator;
	
	ConnectionTCPServer connectionTCPServer;	
	
	JTextField portJTextField;
	
	private JTextArea console;
	
	private JScrollPane scroll;
	
	public static ServerEmulator getInstance() {
		
		if(serverEmulator==null) 
			serverEmulator = new ServerEmulator();
		
		return serverEmulator;		
	}	
	
	private ServerEmulator()  {
		
		super();
		
		connectionTCPServer = new ConnectionTCPServer();
		
		configurarVentana();
		inicializarComponentes();
	}

	private void configurarVentana() {
		
		this.setTitle("Server-Emulator");
		this.setSize(650, 480);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}		
		
	private void inicializarComponentes() {
		
		JButton startSever = new JButton();
		startSever.setText("Start Server");
		startSever.setBounds(20, 20, 150, 30);
		startSever.addActionListener(startServerActionListener());
		this.add(startSever);

		JButton stopServer = new JButton();
		stopServer.setText("Stop Server");
		stopServer.setBounds(190, 20, 150, 30);
		stopServer.addActionListener(stopServerActionListener());
		this.add(stopServer);
		
		JLabel portLabel = new JLabel();
		portLabel.setText("port: ");		
		portLabel.setBounds(370, 20, 150, 30);
		this.add(portLabel);
				
		portJTextField = new JTextField();
		portJTextField.setBounds(430, 20, 70, 30);
		portJTextField.setText("6789");
		this.add(portJTextField);
				
		JLabel serverConsoleLogLabel = new JLabel();
		serverConsoleLogLabel.setText("Server Console Log");		
		serverConsoleLogLabel.setBounds(20, 60, 150, 30);
		this.add(serverConsoleLogLabel);		
		
		console = new JTextArea();
		console.setBounds(20, 100, 600, 280);
		
		scroll = new JScrollPane(console);
		scroll.setBounds(20, 100, 600, 280);
		this.add(scroll);
		
		JButton clearJButton = new JButton();
		clearJButton.setText("Clear");
		clearJButton.setBounds(20, 400, 150, 30);
		clearJButton.addActionListener(clearConsoleActionListener());
		this.add(clearJButton);		
		
		
	}

	private ActionListener startServerActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if( !"".equals(portJTextField.getText())) {
					
					connectionTCPServer.initSession(Integer.valueOf(portJTextField.getText()));
					
					Thread thread = new Thread(){
						
						public void run(){
							
							while(true) {
								
								try {
									
									String listenMessage = connectionTCPServer.listenMessage();

									// System.out.println(listenMessage);
									ServerEmulator.getInstance().log(listenMessage);
									
								} catch (Exception e) {
									
									//System.out.println(e.getMessage());
									ServerEmulator.getInstance().log(e.getMessage());
									connectionTCPServer.closeSession();
									break;
								}
							}
													
						}
					};
					
					thread.start();				
					
					
				}else {
					// Debe ingresar un puerto
					JOptionPane.showMessageDialog(null, "Port is Mandatory");
				}
			}
		};
	}
		
	private ActionListener stopServerActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				connectionTCPServer.closeSession();
			}
		};
	}
	
	private ActionListener clearConsoleActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				console.setText("");
			}
		};
	}	
	
	public static void main(String[] args) {

		ServerEmulator serverEmulator = getInstance();
		serverEmulator.setVisible(true);
	}

	public void log(String logMessage) {
				
		this.console.setText(this.console.getText() + "\n" + logMessage);
				
		
	}
		
}
