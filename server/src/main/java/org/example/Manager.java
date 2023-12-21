package org.example;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Manager implements Runnable{
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public final static ArrayList<Manager> clients = new ArrayList<>();

    public Manager(Socket socket) throws IOException {
        this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");



    }

    @Override
    public void run() {
        String massageFromClient;

        while (socket.isConnected()) {
            try {
                massageFromClient = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                broadcastMessage(massageFromClient);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void broadcastMessage(String message) throws IOException {
        for (Manager client: clients) {
                if (!client.name.equals(name)) {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }

        }
    }

}
