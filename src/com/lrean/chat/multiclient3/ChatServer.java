package com.lrean.chat.multiclient3;

import com.lrean.chat.multiclient.Client;
import com.lrean.chat.multiclient2.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private static Set<ClientHandler>clientHandlers = new HashSet<>();

    public static void main(String[] args)throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Chat server started on port 5000...");
        System.out.println("WELCOME!");

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("New client connected: " + socket.getInetAddress());

            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandlers.add(clientHandler);

            new Thread(clientHandler).start();
        }
    }

    public static void broadcast(String message,ClientHandler excludeUser){
        for (ClientHandler aClint : clientHandlers){
            if (aClint != excludeUser){
                aClint.sendMessage(message);
            }
        }
    }

    public static void removeClient(ClientHandler clientHandler){
        clientHandler.remove(clientHandler);
    }
}
