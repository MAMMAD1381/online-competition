package main;

import UIAndControllers.MainMenu;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Server implements Runnable{
    private int port;
    private ServerSocket serverSocket;
    private Socket server;
    public static int numberOfUsers=4;
    private static ArrayList<ClientHandler> users=new ArrayList<ClientHandler>();

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
            newUser.setOnline(true);
            users.add(newUser);
            MainMenu.updateUsers(getUsernames());
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




    //function that looks for the receiver name and sending the message
    public void chat(String msg ,String receiver,ClientHandler sender) {
        for(ClientHandler user:users){
            if(user.username.equals(receiver)) {
                user.sendMessage(msg);
                System.out.println("msg:  "+msg);
            }
        }
    }

    public ArrayList<String> getUsernames(){
        ArrayList<String> names = new ArrayList<>();
        for(ClientHandler user:users){
            if(user.isOnline()) {
                Text onlineText = new Text("online");
                onlineText.setFill(Color.GREEN);
                names.add(user.username + "\t\t\t\t\t\t" + onlineText.getText());
            }
            else {
                Text offlineText = new Text("offline");
                offlineText.setFill(Color.RED);
                names.add(user.username + "\t\t\t\t\t\t" + offlineText.getText());
            }
            System.out.println(user.username);
        }
        return names;
    }
    private Socket getServer() {
        return server;
    }

    public static void getFriends(ClientHandler client){
        client.sendMessage("Friends");
        for (ClientHandler user:users) {
            client.sendMessage(user.username);
        }
        client.sendMessage("finish");
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

    public static void showScoreBoard(){
        Collections.sort(users);
        for(ClientHandler user:users){
            user.sendMessage("scoreBoard");
            for (ClientHandler u:users) {
                user.sendMessage(u.username);
                user.sendMessage(u.getScore()+"");
                System.out.println("scoreBoard"+u.score+"\n"+u.username);
            }
            user.sendMessage("finishBoard");
        }
    }

    @Override
    public void run() {
        System.out.println("yes");
    }
}
