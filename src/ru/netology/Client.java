package ru.netology;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {

        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            String message;
            while (true) {
                System.out.println("Вычисление N-го члена ряда Фибоначчи.\n" +
                        "Введите целое число");
                message = scanner.nextLine();
                out.println(message);

                System.out.printf("SERVER: %s\n", in.readLine());
                if (message.equals("end")) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
