package fr.ubordeaux.ao.referencemanagement.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerMain {
    final int PORT_NUMBER = 8080;
    private ServerSocket serverSocket;

    public SocketServerMain() {
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            handleClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    private void handleClient() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New Client connected");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            TextualMenu tm = new TextualMenu(in, out);
            tm.handleUserInstructions();
            System.out.println("Close connection");
            clientSocket.close();
        }
    }

    public static void main(String[] args) {
        SocketServerMain socSer = new SocketServerMain();        
    }
    
}