package org.utnfrd.chat.util;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ChatUtil {
	
	static ChatUtil chatUtil;
	
	Map<String, Socket> usuarios = new HashMap<String, Socket>();

	private ChatUtil() {
		super();
	}

	public static ChatUtil getInstance() {
						
		if(chatUtil == null) chatUtil = new ChatUtil();
			
		return chatUtil;		
	}

	public Map<String, Socket> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Map<String, Socket> usuarios) {
		this.usuarios = usuarios;
	}
		
}
