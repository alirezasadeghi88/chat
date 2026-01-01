package com.lrean.chat.multiclient3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String userName;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("Enter your name: ");
            userName = in.readUTF();

            ChatServer.broadcast(userName + " joined the chat",this);
        }catch (IOException e){
            closeEverything();
        }
    }

    @Override
    public void run() {
        String message;
        while (true){
            try {
                message = in.readUTF();
                System.out.println(userName + " : " + message);
                ChatServer.broadcast(userName + " : " + message,this);
            }catch (IOException e){
                closeEverything();
                break;
            }
        }
    }

    public void sendMessage(String message){
        try {
            out.writeUTF(message);
        }catch (IOException e){
            closeEverything();
        }
    }

    private void closeEverything() {
        ChatServer.removeClient(this);
        try {
            if (in != null)in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();

            ChatServer.broadcast(userName + "left the chat",this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
