package org.utnfrd.chat.connection;

import java.io.IOException;

public interface Connection {
	
	public String initSession(int localPort, String toIP, int toPort, String userName);

	public void closeSession();
	
	public void sendMessage(String message);
	
	public String listenMessage() throws IOException;
}
