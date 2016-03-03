package server; 

import java.net.*;
import java.io.*;

/**
 * @author Thomas Taschner & Michael Weinberger
 * @version 03.03.2016
 * 
 * lorem ipsum
 *
 */

public class Server {
	
	private ServerSocket serverSocket;
	
	public Server(int port) throws IOException
	   {
	      serverSocket = new ServerSocket(port);
	      serverSocket.setSoTimeout(10000);
	   }
	
	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt(args[0]);
		
	     ServerThread t = new ServerThread(port);
		 t.run();
		 System.out.println(t.getOut());
	}
}