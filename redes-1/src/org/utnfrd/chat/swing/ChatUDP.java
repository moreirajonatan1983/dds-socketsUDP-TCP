/**
 * 
 */
package org.utnfrd.chat.swing;

/**
 * @author jonatan.moreira
 *
 */
public class ChatUDP {

	
	public static void main(String[] args) {

//		ChatEmulator chatClient0 = new ChatEmulator("127.0.0.1" , 6789, "127.0.0.1" , 6789, "Jonatan", "Ana");
//		chatClient0.setVisible(true);		
//
//		ChatEmulator chatClient00 = new ChatEmulator("127.0.0.1" , 6789, "127.0.0.1" , 6789, "Ana", "Jonatan");
//		chatClient00.setVisible(true);		
		
		
		ChatEmulator chatClient1 = new ChatEmulator("127.0.0.1" , 9876, "127.0.0.1" , 9878, "Jonatan", "Ana");
		chatClient1.setVisible(true);

		ChatEmulator chatClient2 = new ChatEmulator("127.0.0.1" , 9878, "127.0.0.1" , 9876,"Ana", "Jonatan");
		chatClient2.setVisible(true);

		
	}
	
}
