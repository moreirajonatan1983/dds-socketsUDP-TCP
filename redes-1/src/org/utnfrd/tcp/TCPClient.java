package org.utnfrd.tcp;

import java.io.*;

import java.net.*;

/**
 * NOTA: antes de iniciar el cliente se tiene que levantar el TCPServer
 * 
 * @author jonatanmoreira
 *
 */
public class TCPClient {

	public static void main(String argv[]) throws Exception {

		try {
			
			String sentence;

			String modifiedSentence;

			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

			Socket clientSocket = new Socket("127.0.0.1", 6789);

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			sentence = inFromUser.readLine();

			outToServer.writeBytes(sentence + '\n');

			modifiedSentence = inFromServer.readLine();

			System.out.println("FROM SERVER: " + modifiedSentence);

			clientSocket.close();

		} catch (Exception e) {

			System.out.println("El servidor no esta levantado.");

			throw e;
		}
	}
}
