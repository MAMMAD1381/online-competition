package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable{
    private int port;
    private ServerSocket serverSocket;
    private Socket server;
    public static int numberOfUsers=4;

    public Server(int port) throws IOException {
        setPort(port);
        try {
            setServerSocket(new ServerSocket(getPort()));
        }
        catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
        while (true){
            ready();
            Thread t=new Thread(new ClientHandler(server));
            t.start();
        }

    }

    private int getPort() {
        return port;
    }

    private void setPort(int port) {
        this.port = port;
    }

    private ServerSocket getServerSocket() {
        return serverSocket;
    }

    private void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void sendMessage(String message) throws IOException {
        OutputStream toUser = getServer().getOutputStream();
        PrintWriter sender = new PrintWriter(toUser, true);
        sender.println(message);
    }

    public String receiveMessage() throws IOException {

        String message;
        InputStream fromUser = getServer().getInputStream();
        Scanner scanner = new Scanner(new DataInputStream(fromUser));
        message = scanner.next();
        System.out.println(message);
        return message;
    }

    private Socket getServer() {
        return server;
    }

    private void setServer(Socket server) {
        this.server = server;
    }

    private void ready() throws IOException {

        setServer(getServerSocket().accept());
        numberOfUsers++;
    }

    public void close() throws IOException {
        getServerSocket().close();
    }

    @Override
    public void run() {
        System.out.println("yes");
    }
}
