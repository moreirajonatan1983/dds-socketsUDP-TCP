package org.utnfrd.chat.swing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.utnfrd.chat.connection.Connection;
import org.utnfrd.chat.connection.impl.ConnectionTCPServer;

public class Server {

	private static int SERVER_PORT = 6789;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ConnectionTCPServer connection = new ConnectionTCPServer();
		
		while(true) {
			
			BufferedReader inFromUser =	new BufferedReader(new InputStreamReader(System.in));
			String sentence = inFromUser.readLine();
			
			if("start".equalsIgnoreCase(sentence)) {
				
				connection.initSession(SERVER_PORT);								
				
				Thread.sleep(500);	
				
				Thread thread = new Thread(){
					
					public void run(){
						
						while(true) {
							
							try {
								
								String listenMessage = connection.listenMessage();

								System.out.println(listenMessage);
								
							} catch (Exception e) {
								
								System.out.println(e.getMessage());
								
								connection.closeSession();
								break;
							}
						}
												
					}
				};
				
				thread.start();				
								
			} if("stop".equalsIgnoreCase(sentence)) {
				
				connection.closeSession();
			
			} if("exit".equalsIgnoreCase(sentence)) {
				
				connection.closeSession();
				break;
			}
			
		}

	}

}
