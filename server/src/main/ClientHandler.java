package main;

import UIAndControllers.MainMenu;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler  extends Thread implements Comparable<ClientHandler>{
    private Socket client;
    private Server server;
    public String username;
    public int score;

    public static int counter = 0;//todo delete this shit
    private Scanner scanner;
    private PrintWriter sender;
    ArrayList<Question> question;
    private boolean isOnline;
    public ClientHandler(Socket socket ,Server server){
        this.client = socket;
        this.server=server;
        InputStream fromUser = null;
        try {
            fromUser = client.getInputStream();
            scanner = new Scanner(new DataInputStream(fromUser));
            OutputStream toUser = client.getOutputStream();
            sender = new PrintWriter(toUser, true);

            username=scanner.nextLine() +" "+counter;
            counter++;//todo test usernames


            score=0;
        } catch (IOException e) {

        }
        question=new ArrayList<>();
    }


    //function to send the questions to clients
    public void test(){
        Quiz.getQuestion(question);
        sendMessage("test");
        for (int i=0;i< question.size();i++){
            sendMessage(question.get(i).question);
            sendMessage(question.get(i).options[0]);
            sendMessage(question.get(i).options[1]);
            sendMessage(question.get(i).options[2]);
            sendMessage(question.get(i).options[3]);

        }
        sendMessage("finish");
        setScore();

    }


    public void setScore(){
        for (int i=0;i< question.size();i++){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            sendMessage("answer");
            if(receiveMessage().equals(question.get(i).answer+""))
                score++;

            Server.showScoreBoard();
//

        }
    }


    //revives message from 1 user and sends it to another
    public void chat(){
        String msg;
        String receiver=scanner.nextLine();

        server.chat("chat",receiver,this);
        server.chat(username ,receiver,this);
        while (true){
            msg=scanner.nextLine();
            if(msg.equals("finish"))
                break;
            server.chat(msg ,receiver ,this);
        }




    }



    public void sendMessage(String message){
        try {
            sender.println(message);

        }
        catch (Exception e){

        }
    }


    //receiving data from client
    public String receiveMessage(){

        String message;
        message = scanner.nextLine();

        switch (message){
            case "chat":
                chat();
                break;
            case "test":
                test();
                break;
            case "Friends":
                Server.getFriends(this);
                break;

        }
        return message;
    }


    @Override
    public void run() {
        try {
            while (true){
                receiveMessage();
            }
        }catch (java.util.NoSuchElementException e){
            setOnline(false);
            System.out.println("user disconnected");
            MainMenu.updateUsers(server.getUsernames()); //todo resending the users after offlining them
        }

    }

    public int getScore() {
        return score;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    @Override
    public int compareTo(ClientHandler clientHandler) {
        int compare=(clientHandler.score);
        return compare-this.score;
    }
}
