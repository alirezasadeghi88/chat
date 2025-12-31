package com.lrean.chat;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // اتصال به سرور روی localhost پورت 8000
            Socket socket = new Socket("localhost", 8000);
            System.out.println("Connected to server!");

            // جریان‌های ورودی و خروجی برای ارتباط با سرور
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            // حلقه بی‌نهایت برای ارسال و دریافت پیام
            while (true) {
                // خواندن پیام از کنسول کلاینت
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Client: ");
                String message = console.readLine();

                // ارسال پیام به سرور
                output.writeUTF(message);

                // دریافت پاسخ از سرور
                String reply = input.readUTF();
                System.out.println("Server: " + reply);
            }
        } catch (IOException ex) {
            // مدیریت خطاهای ورودی/خروجی
            ex.printStackTrace();
        }
    }
}
