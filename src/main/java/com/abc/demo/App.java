package com.abc.demo;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
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
                    DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            ) {
                System.out.println("Socket Server started... port=" + PORT);

                String line = "";
                while (! "exit".equals(line)) {
                    try {
                        line = in.readUTF();
                        System.out.println(line);

                    } catch (IOException i) {
                        System.out.println(i);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
