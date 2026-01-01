package com.lrean.chat.multiclient2;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8000);
            System.out.println("Connected to server!");

            DataInputStream input = new DataInputStream(
                    socket.getInputStream());
            DataOutputStream output = new DataOutputStream(
                    socket.getOutputStream());

            while (true){
                BufferedReader console = new BufferedReader(new
                        InputStreamReader(System.in));
                System.out.println("Client: ");
                String message = console.readLine();

                output.writeUTF(message);

                String reply = input.readUTF();
                System.out.println("Server: " + reply);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
