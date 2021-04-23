package org.utnfrd.chat.connection.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.utnfrd.chat.swing.ServerEmulator;
import org.utnfrd.chat.util.ChatUtil;

public class ConnectionTCPServer {
	
	private ServerSocket serverSocket; // puerto en donde escucha el servidor
		
	public void initSession(int localPort) {
		
		try {
			
			serverSocket = new ServerSocket(localPort);			
			
			ServerEmulator.getInstance().log("Servidor iniciado. Port: " + localPort);
			
		} catch (UnknownHostException e) {
			
			ServerEmulator.getInstance().log(e.getMessage());
			
		} catch (IOException e) {
			
			ServerEmulator.getInstance().log(e.getMessage());
		}
		
	}
	
	public void closeSession() {
		
		try {
			serverSocket.close();
		} catch (Exception e) {
						
			ServerEmulator.getInstance().log(e.getMessage());
		}

	}
	
	public void sendMessage(Socket connectionSocket, String message) {
		
		try {
			
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			outToClient.writeBytes(message + "\n");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());			
		}
		
	}

	
	public String listenMessage() throws IOException {

		Socket connectionSocket;
		
		// se queda esperando para aceptar la conexion con un cliente.		
		ServerEmulator.getInstance().log("Esperando conexion de algún cliente. (serverSocket.accept())");
		connectionSocket = serverSocket.accept();
				
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		
		Thread thread = new Thread(){
			
			public void run(){		
				try {
					listenClient(connectionSocket, inFromClient);
				} catch (Exception e) {
					
					ServerEmulator.getInstance().log("Error message: " + e.getMessage() + "Error cause: " + e.getCause());
				}
			}
		};
		thread.start();
		
		return "";
	}

	private void listenClient(Socket connectionSocket, BufferedReader inFromClient) throws IOException {
				
		try {
			ServerEmulator.getInstance().log("Conexión aceptada. Esperando mensaje del cliente.");
			
			int count = 0;
			while(count<5){
				
				
				ServerEmulator.getInstance().log("Esperando mensaje del cliente");
				// se queda esperando el mensaje del cliente.
				String messageClient = inFromClient.readLine();
				
				ServerEmulator.getInstance().log("Mensaje del cliente recibido: " + messageClient);			
				
				// El primer mensaje contiene el nombreDelCliente
				if(count==0) {
					
					ChatUtil.getInstance().getUsuarios().put(messageClient, connectionSocket);				
				} else {
	
					String toUser = messageClient.split("\\|\\|")[0];
					String message = messageClient.split("\\|\\|")[1];
					
					ChatUtil.getInstance().getUsuarios().get(toUser);
									
					// enviar mensaje a cliente
					sendMessage(ChatUtil.getInstance().getUsuarios().get(toUser), message);				
				}
				
				count++;
			}		
			ServerEmulator.getInstance().log("Conexión cerrada. Debe volver a iniciar sesion.");
		} catch (Exception e) {
			// Conexion cerrada.
		}
		
	}
	
}