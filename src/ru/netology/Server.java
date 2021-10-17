package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

                System.out.println("New connection accepted " + clientSocket.getInetAddress());

                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) break;

                    try {
                        int numberInFibonacci = Integer.parseInt(line);
                        out.println(numberInFibonacci + " член ряда Фибоначчи: " + String.format("%,d", fibonacci(numberInFibonacci)));
                    } catch (NumberFormatException e) {
                        out.println("Ожидается число");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
            } finally {
                System.out.println("Disconnected");
            }
        }
    }

    private static long fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n <= -1) {
            return fibonacci(n + 2) - fibonacci(n + 1);
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
