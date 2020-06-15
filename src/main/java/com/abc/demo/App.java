package com.abc.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

    private static final int PORT = 5000;

    public static void main(String[] args) {
        new SocketServer(PORT);
    }

    static class SocketServer {

        SocketServer(int port) {
            System.out.println("Socket Server starting...");
            try (
                    ServerSocket serverSocket = new ServerSocket(port);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                System.out.println("Socket Server started, port=" + PORT);
                String inputLine, outputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Socket Client: " + inputLine);
                    if ("exit".equalsIgnoreCase(inputLine)) {
                        break;
                    }

                    outputLine = inputLine + " - from Socket Server";
                    out.println(outputLine);
                }
                System.out.println("Socket Server closed");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
