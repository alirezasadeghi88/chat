package com.lrean.chat.multiclient2;

import java.io.*;
import java.net.Socket;

public class HandleClient implements Runnable{
    private Socket socket;

    public HandleClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(
                    socket.getInputStream());
            DataOutputStream output = new DataOutputStream(
                    socket.getOutputStream());

            while (true){
                String message = input.readUTF();
                System.out.println("Client says: " + message);

                BufferedReader console = new BufferedReader(new
                        InputStreamReader(System.in));
                System.out.println("Server reply: ");
                String reply = console.readLine();
                output.writeUTF(reply);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
