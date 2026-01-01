package com.lrean.chat.multiclient2;

import com.lrean.chat.multiclient.HandleClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started on port 8000...");

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket);

                new Thread(new HandleClient(socket)).start();

            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
