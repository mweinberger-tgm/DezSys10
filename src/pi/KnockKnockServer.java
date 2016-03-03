package pi;

import java.net.*;
import java.io.*;

/**
 * @author Thomas Taschner
 * @version 18.10.2014
 * 
 * Diese Klasse lässt eine Punkt-zu-Punkt Verbindung mit einem Client aufbauen, der Nachrichten an den Server sendet und dann dementsprechende Rückmeldungen zurückliefert, die auf der Konsole ausgegeben werden.
 *
 */

public class KnockKnockServer {
	public static void main(String[] args) throws IOException {
		
		// Fehlermeldung wird bei ungültiger Parameteranzahl ausgegeben
		if (args.length != 1) {
			System.err.println("Usage: java KnockKnockServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		try (
				// Neuer Server Socket an gegebener Adresse wird erstellt
				ServerSocket serverSocket = new ServerSocket(portNumber);
				
				// Neuer (Client) Socket wird an den Server Socket gebunden
				Socket clientSocket = serverSocket.accept();
				
				// Output des Sockets wird soll ausgegeben werden
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				
				// Client Input wird zwischengespeichert
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				)
				{

			String inputLine, outputLine;

			// Konversion mit dem Client wird gestartet
			KnockKnockProtocol kkp = new KnockKnockProtocol();
			outputLine = kkp.processInput(null);
			out.println(outputLine);
			
			// Solange der Input nicht null ist, wird dieser in KnockKnockProtol verarbeitet, ein entsprechender Text zurückgegeben und abschließend auf der Konsole ausgegeben.
			while ((inputLine = in.readLine()) != null) {
				outputLine = kkp.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("Bye."))
					break;
			}
				} catch (IOException e) {
					System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
					System.out.println(e.getMessage());
				}
	}
}