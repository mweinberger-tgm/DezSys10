package pi;

import java.io.*;
import java.net.*;

/**
 * @author Thomas Taschner
 * @version 18.10.2014
 * 
 * Diese Klasse baut eine Punkt-zu-Punkt Verbindung mit einem Server auf, der abhängig vom Nachrichteninhalt des Clients entsprechende Rückmeldungen liefert, die auf der Konsole ausgegeben werden.
 *
 */

public class KnockKnockClient {
	public static void main(String[] args) throws IOException {
		
		// Fehlermeldung wird bei ungültiger Parameteranzahl ausgegeben
		if (args.length != 2) {
			System.err.println("Usage: java EchoClient <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		try (
				// Neuer (Client) Socket an gegebener Adresse wird erstellt
				Socket kkSocket = new Socket(hostName, portNumber);
				
				// Output des Sockets wird soll ausgegeben werden
				PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
				
				// Client Input wird zwischengespeichert
				BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
				) {
			
			// Konsoleninput wird zwischengespeichert
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;

			// Es soll solange der Input des Clients und der Output des Servers ausgegeben werden, bis der Server nicht mehr antwortet (-> der Output der Servers null ist)
			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("Bye."))
					break;

				fromUser = stdIn.readLine();
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					out.println(fromUser);
				}
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}
}
