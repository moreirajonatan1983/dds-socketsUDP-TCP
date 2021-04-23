package org.utnfrd.chat.connection.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.utnfrd.chat.connection.Connection;
import org.utnfrd.chat.swing.ChatConsoleLogEmulator;

public class ConnectionUDP implements Connection{
		
	
	// https://github.com/netosolis1990/chat-sockets-threads-java/blob/master/src/chatservidor/Servidor.java
	
	private DatagramSocket clientDatagramSocket;
	
	private DatagramSocket sendDatagramSocket;	
	
	private String toIP;

	private int toPort;	
	
	@Override
	public String initSession(int localPort, String toIP, int toPort, String userName) {
		
		try {
			this.toIP = toIP;
			this.toPort = toPort;
			
			ChatConsoleLogEmulator.getInstance().log("Iniciando Sesión. ip:port: " + toIP + ":" + localPort);
			sendDatagramSocket = new DatagramSocket();
			clientDatagramSocket = new DatagramSocket(localPort);			
		} catch (Exception e) {			
			
			ChatConsoleLogEmulator.getInstance().log("Error al iniciar Sesión.");			
		}
		
		return "";
	}

	@Override
	public void closeSession() {
		
		ChatConsoleLogEmulator.getInstance().log("Cerrando Sesión.");
		clientDatagramSocket.close();
	}
	
	@Override
	public void sendMessage(String message) {

		message = message.split("\\|\\|")[1];;
		try {
		
			InetAddress IPAddress = InetAddress.getByName(toIP);
			
			byte[] sendData = message.getBytes();
			
			DatagramPacket sendPacket =	new DatagramPacket(sendData, sendData.length, IPAddress, toPort);

			ChatConsoleLogEmulator.getInstance().log("Enviando mensaje a: " + toIP + ":" + toPort);
			sendDatagramSocket.send(sendPacket);
			
		} catch (Exception e) {			
			
			ChatConsoleLogEmulator.getInstance().log("Error al enviar mensaje.");			
		}
		
	}
	
	public String listenMessage() throws IOException {

		byte[] receiveData = new byte[1024];
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);		
		
		ChatConsoleLogEmulator.getInstance().log("Cliente escuchando para recibir mensaje.");
		clientDatagramSocket.receive(receivePacket);
		return new String(receivePacket.getData());
			
	}
}
