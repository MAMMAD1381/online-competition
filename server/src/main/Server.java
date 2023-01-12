package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server implements Runnable{
    private int port;
    private ServerSocket serverSocket;
    private Socket server;
    public static int numberOfUsers=4;

    private ArrayList<ClientHandler> users=new ArrayList<ClientHandler>();

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
            System.out.println("connected");
            ClientHandler newUser=new ClientHandler(server ,this);
            users.add(newUser);
            newUser.start();
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



    public void chat(String msg ,String receiver,ClientHandler sender) throws IOException {
        for(ClientHandler user:users){
            if(user.username.equals(receiver)) {
                user.sendMessage("[" + sender.username + "]" + msg);
                System.out.println("chat success");
            }
        }
    }

    public void getUsernames(ArrayList<String> names){

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
