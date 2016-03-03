package server;

import java.net.*;
import java.io.*;

/**
 * @author Thomas Taschner & Michael Weinberger
 * @version 03.03.2016
 *          <p>
 *          lorem ipsum
 */

public class Server implements Runnable {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Warte auf Anfrage auf Port " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Verbunden mit " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                PiCalculator compute = new PiCalculator();
                out.writeUTF(compute.pi(in.readInt()).toString());
                server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        int port = 3333;
        try {
            Thread t = new Thread(new Server(port));
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}