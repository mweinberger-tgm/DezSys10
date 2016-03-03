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

public class KKMultiServerThread implements Runnable {
	private Socket socket = null;

	public KKMultiServerThread(Socket socket) {
		//super("KKMultiServerThread");
		this.socket = socket;
	}

	public void run() {

		try (
				// Output des Sockets wird soll ausgegeben werden
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				
				// Client Input wird zwischengespeichert
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				) {
			String inputLine, outputLine;
			
			// Konversion mit dem Client wird gestartet
			KnockKnockProtocol kkp = new KnockKnockProtocol();
			outputLine = kkp.processInput(null);
			out.println(outputLine);
			
			// Solange der Input nicht null ist, wird dieser in KnockKnockProtol verarbeitet, ein entsprechender Text zurückgegeben und abschließend auf der Konsole ausgegeben.
			while ((inputLine = in.readLine()) != null) {
				outputLine = kkp.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("Bye"))
					break;
			}
			// Verbindung zum Socket wird geschlossen
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}