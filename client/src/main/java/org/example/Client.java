package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static String name;
    static Socket socket;
    static BufferedWriter bufferedWriter;
    static BufferedReader bufferedReader;

    public Client(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }
    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (socket.isConnected()){
                    try {
                        message = bufferedReader.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(message);
                    }
            }
        }).start();
    }
    public void sendMessage() throws IOException {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                bufferedWriter.write(name + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("привет клиент!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("представься");
        name = scanner.nextLine();
        socket = new Socket("localhost",1400);
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Client client = new Client(socket,name);
        client.listenForMessage();
        client.sendMessage();


    }
}