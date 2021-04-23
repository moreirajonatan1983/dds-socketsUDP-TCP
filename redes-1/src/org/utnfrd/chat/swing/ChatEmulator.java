package org.utnfrd.chat.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.utnfrd.chat.connection.Connection;
import org.utnfrd.chat.connection.impl.ConnectionTCPClient;
import org.utnfrd.chat.connection.impl.ConnectionUDP;

public class ChatEmulator extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3348569310461686884L;

	// private Connection connection = new ConnectionUDP();
	
	private Connection connection = new ConnectionTCPClient();
	
	private String fromName;
	
	private String toName;
	
	private JRadioButton jRadioButtonTCP = new JRadioButton("TCP");
	
	private JRadioButton jRadioButtonUDP = new JRadioButton("UDP");
	
	private ButtonGroup buttonGroupTypeConnection = new ButtonGroup();
	
	private JLabel labelProtocolType;
	private JLabel fromIpPortJLabel;
	private JTextField fromIpPortJTextField;
	
	JTextField toIpPortJTextField;

	JTextField toNameTextField;
	
	private JLabel texto;
	private JTextField messageTextField;
	private JButton buttonSend;
	private JTextArea textArea;

	private JLabel toIpPortLabel;

	private JLabel toLabel;

	private JLabel fromNameLabel;

	private JButton buttonClear;

	private JButton buttonInitSession;

	private JButton buttonCloseSession;

	private JTextField fromNameTextField;

	private String fromIpPort;

	private String toIpPort;
	
	public ChatEmulator(String fromIp, int fromPort, String toIp, int toPort, String fromName, String toName) {

		super();
		
		this.fromIpPort = "";
		this.toIpPort = toIp + ":" + toPort;		
		
		this.fromName = fromName;
		this.toName = toName;
		
		configurarVentana();
		inicializarComponentes();
	}

	private void configurarVentana() {
		
		this.setTitle("Chat-Emulator");
		this.setSize(310, 560);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void inicializarComponentes() {
		
		labelProtocolType = new JLabel();		
		jRadioButtonTCP.setText("TCP");
		jRadioButtonUDP.setText("UDP");
		
		labelProtocolType.setBounds(20, 15, 110, 25);
		jRadioButtonTCP.setBounds(140,15,80,25);    
		jRadioButtonUDP.setBounds(220,15,80,25);
		
		jRadioButtonTCP.addActionListener(selectedTCPActionListener());
		jRadioButtonUDP.addActionListener(selectedUDPActionListener());
		
		labelProtocolType.setText("Protocol");
		buttonGroupTypeConnection.add(jRadioButtonTCP);
		buttonGroupTypeConnection.add(jRadioButtonUDP);		
						
		jRadioButtonTCP.setSelected(true);
		
		this.add(labelProtocolType);
		this.add(jRadioButtonTCP);
		this.add(jRadioButtonUDP);
		
		// ----------------------------------------

		buttonInitSession = new JButton();
		buttonInitSession.setText("Init Session");
		buttonInitSession.setBounds(20, 60, 130, 30);
		
		buttonInitSession.addActionListener(initSessionActionListener());
		
		this.add(buttonInitSession);
		
		// ----------------------------------------
		buttonCloseSession = new JButton();
		buttonCloseSession.setText("Close Session");
		buttonCloseSession.setBounds(160, 60, 135, 30);
		
		buttonCloseSession.addActionListener(closeSessionActionListener());
		
		this.add(buttonCloseSession);		
		
		// ----------------------------------------
		
		fromIpPortJLabel = new JLabel();
		fromIpPortJTextField = new JTextField();
		
		fromIpPortJLabel.setBounds(20, 100, 110, 25);
		fromIpPortJTextField.setBounds(140, 100, 150, 25);
		
		fromIpPortJLabel.setText("From [IP:PORT]");
		fromIpPortJTextField.setText(this.fromIpPort);		
		
		this.add(fromIpPortJLabel);
		this.add(fromIpPortJTextField);
		// ----------------------------------------
		
		toIpPortLabel = new JLabel();
		toIpPortJTextField = new JTextField();
		
		toIpPortLabel.setBounds(20, 130, 110, 25);
		toIpPortJTextField.setBounds(140, 130, 150, 25);
		
		toIpPortLabel.setText("To [IP:PORT]");
		toIpPortJTextField.setText(this.toIpPort);		
		
		this.add(toIpPortLabel);
		this.add(toIpPortJTextField);
		// ----------------------------------------
		
		fromNameLabel = new JLabel();
		fromNameTextField = new JTextField();
		
		fromNameLabel.setBounds(20, 160, 150, 25);
		fromNameTextField.setBounds(140, 160, 150, 25);
		
		fromNameLabel.setText("From:");
		fromNameTextField.setText(this.fromName);
		
		this.add(fromNameLabel);
		this.add(fromNameTextField);
		
		// ----------------------------------------		
		toLabel = new JLabel();
		toNameTextField = new JTextField();
		
		toLabel.setBounds(20, 190, 150, 25);
		toNameTextField.setBounds(140, 190, 150, 25);
		
		toNameTextField.setText(toName);
		toLabel.setText("To:");
		
		
		this.add(toLabel);
		this.add(toNameTextField);
		
		// ----------------------------------------
		
		textArea = new JTextArea();	
		texto = new JLabel();
		messageTextField = new JTextField();		
				
		textArea.setBounds(20, 230, 270, 200);		
		texto.setBounds(20, 50, 150, 100);
		messageTextField.setBounds(20, 450, 280, 25); // mensaje a enviar
				
		textArea.setEditable(false);
		
		messageTextField.addActionListener(sendActionListener());
		
		this.add(textArea);
		this.add(texto);
		this.add(messageTextField);
		
		// ----------------------------------------
		buttonClear = new JButton();
		buttonClear.setText("Clear");
		buttonClear.setBounds(90, 480, 100, 30);
		buttonClear.addActionListener(clearActionListener());
		this.add(buttonClear);
		
		// ----------------------------------------
		buttonSend = new JButton();
		buttonSend.setText("Send");
		buttonSend.setBounds(200, 480, 100, 30);
		
		buttonSend.addActionListener(sendActionListener());
		
		this.add(buttonSend);
		
	}

	private ActionListener selectedTCPActionListener() {

		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connection = new ConnectionTCPClient();
				
				fromIpPortJTextField.setText("");
				toIpPortJTextField.setText("127.0.0.1:6789");				
				
				toLabel.setVisible(true);
				toNameTextField.setVisible(true);
				
			}
		};		
	}

	private ActionListener selectedUDPActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
						
				connection = new ConnectionUDP();

				fromIpPortJTextField.setText("127.0.0.1:9876");
				toIpPortJTextField.setText("127.0.0.1:9877");
				
				toLabel.setVisible(false);
				toNameTextField.setVisible(false);				
				
			}
		};	
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String nombre = messageTextField.getText();
		JOptionPane.showMessageDialog(this, "Hola " + nombre + ".");
	}

	private ActionListener initSessionActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int localPort=0;
				
				if(!"".equals(fromIpPortJTextField.getText()))
					localPort = Integer.valueOf( fromIpPortJTextField.getText().split(":")[1] );				
				
				String toIP = toIpPortJTextField.getText().split(":")[0] ;
				int toPort = Integer.valueOf( toIpPortJTextField.getText().split(":")[1] );;
				
				String ipPort = connection.initSession( localPort, toIP, toPort , fromNameTextField.getText());
				
				if(!"".equals(ipPort)) fromIpPortJTextField.setText(ipPort);				
				
				Thread thread = new Thread(){
					
					public void run(){
						
						boolean entrar = true;
						while(entrar) {
							
							try {
								
								String listenMessage = connection.listenMessage();
								String txt  =((textArea.getText().isEmpty() || textArea.getText().isBlank())?"":textArea.getText() + System.lineSeparator());
								textArea.setText(										 
										txt
										+ listenMessage);
							} catch (Exception e) {
								
								// System.out.println("Error");
								
								connection.closeSession();
								break;
							}
						}
						
						
					}
				};
				
				thread.start();				
				
				
			}
		};
	}	
	
	private ActionListener closeSessionActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connection.closeSession();
			}
		};
	}	
	
	private ActionListener sendActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(!messageTextField.getText().isBlank() && !messageTextField.getText().isEmpty()) {

					connection.sendMessage(toNameTextField.getText() + "||" +  fromNameTextField.getText() + ": " + messageTextField.getText());
					
					textArea.setText(textArea.getText() 
							+ ((textArea.getText().isEmpty() || textArea.getText().isBlank())?"":System.lineSeparator())
							+ fromNameTextField.getText() + ": "+ messageTextField.getText());
					messageTextField.setText("");
					
				}
			}
		};
	}
	
	private ActionListener clearActionListener() {
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				textArea.setText("");
				messageTextField.setText("");
			}
		};
	}
		
}