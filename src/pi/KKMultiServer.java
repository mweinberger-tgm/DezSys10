package pi; 

import java.net.*;
import java.io.*;

/**
 * @author Thomas Taschner
 * @version 18.10.2014
 * 
 * Diese Klasse lässt ein oder mehrere Punkt-zu-Punkt Verbindungen mit einem Client aufbauen, der Nachrichten an den Server sendet und dann dementsprechende Rückmeldungen zurückliefert, die auf der Konsole ausgegeben werden.
 *
 */

public class KKMultiServer {
	public static void main(String[] args) throws IOException {
		
		// Fehlermeldung wird bei ungültiger Parameteranzahl ausgegeben
		if (args.length != 1) {
			System.err.println("Usage: java KKMultiServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);
		boolean listening = true;
		
		// Versuche einen neuen Server Socket auf gegebenem Port zu erstellen und nimm bis zur Terminierung des Programms Client Verbindungen an.
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
			while (listening) {
				new KKMultiServerThread(serverSocket.accept()).run();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}
}