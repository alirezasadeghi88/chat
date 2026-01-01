package com.lrean.chat.multiclient3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);

        System.out.println(in.readUTF());
        String name = scanner.nextLine();
        out.writeUTF(name);

        new Thread(() -> {
            try {
                while (true){
                    String msgFromServer = in.readUTF();
                    System.out.println(msgFromServer);
                }
            }catch (IOException e){
                System.out.println("Connection closed.");
                System.out.println("GOD BAY!");
            }
        }).start();

        while (true){
            String msg = scanner.nextLine();
            out.writeUTF(msg);
        }
    }
}
