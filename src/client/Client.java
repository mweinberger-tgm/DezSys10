package client;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        String serverName = "10.0.0.19";
        int port = 3333;
        int digits; //= 15;
        for (int i = 0; i < 10; i++) {
            try {
                digits = i;
                System.out.println("Verbinde mit " + serverName + " auf Port " + port);
                Socket client = new Socket(serverName, port);
                System.out.println("Erfolgreich verbunden mit " + client.getRemoteSocketAddress());
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);
                out.writeInt(digits);
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.println("Pi berechnet auf " + digits + " Stellen: " + in.readUTF());
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}