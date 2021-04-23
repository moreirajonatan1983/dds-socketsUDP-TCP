package org.utnfrd.chat.connection.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.utnfrd.chat.connection.Connection;
import org.utnfrd.chat.swing.ChatConsoleLogEmulator;
import org.utnfrd.chat.swing.ServerEmulator;

public class ConnectionTCPClient implements Connection {
	
	private Socket clientSocket;
	
	@Override
	public String initSession(int localPort, String toIP, int toPort, String userName) {		
			
		String ipPort="";
		try {					
			
			clientSocket = new Socket("127.0.0.1", toPort);			
			
			sendMessage(userName);
					
			ipPort = String.valueOf(clientSocket.getLocalSocketAddress()).substring(1);
			
			ChatConsoleLogEmulator.getInstance().log("Cliente iniciado. Server: 127.0.0.1:" + toPort + ". Client: " + ipPort);
			
		} catch (IOException e) {
							
			ChatConsoleLogEmulator.getInstance().log(e.getMessage());
							
		}
		return ipPort;
			
	}

	@Override
	public void closeSession() {
		
		try {
			
			ChatConsoleLogEmulator.getInstance().log("Cerrando socket. serverSocket.close()");
			clientSocket.close();
		} catch (Exception e) {
			
			ChatConsoleLogEmulator.getInstance().log(e.getMessage());
		}

	}

	@Override
	public void sendMessage(String message) {
		
		try {
						
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			
			ChatConsoleLogEmulator.getInstance().log("Enviando Mensaje: " + message);
			outToServer.writeBytes(message + '\n');
						
		} catch (Exception e) {
			
			ChatConsoleLogEmulator.getInstance().log(e.getMessage());		
		}
		
	}

	@Override
	public String listenMessage() throws IOException {
		
		ChatConsoleLogEmulator.getInstance().log("Cliente escuchando");
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
		return inFromServer.readLine();
				
	}
	
}