package com.lrean.chat.multiclient2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started on port 8000...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            DataInputStream input = new DataInputStream(
                    socket.getInputStream());
            DataOutputStream output = new DataOutputStream(
                    socket.getOutputStream());

            while (true){
                String message = input.readUTF();
                System.out.println("Client: " + message);

                BufferedReader console = new BufferedReader(
                        new InputStreamReader(System.in));
                System.out.println("Server: ");
                String reply = console.readLine();

                output.writeUTF(reply);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
