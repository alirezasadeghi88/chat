package com.lrean.chat;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // ایجاد سوکت سرور روی پورت 8000
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started on port 8000...");

            // منتظر اتصال کلاینت
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // جریان‌های ورودی و خروجی برای ارتباط با کلاینت
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            // حلقه بی‌نهایت برای دریافت و ارسال پیام
            while (true) {
                // دریافت پیام از کلاینت
                String message = input.readUTF();
                System.out.println("Client: " + message);

                // خواندن پاسخ از کنسول سرور
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Server: ");
                String reply = console.readLine();

                // ارسال پاسخ به کلاینت
                output.writeUTF(reply);
            }
        } catch (IOException ex) {
            // مدیریت خطاهای ورودی/خروجی
            ex.printStackTrace();
        }
    }
}
