package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler  extends Thread implements Comparable<ClientHandler>{
    private Socket client;
    private Server server;
    public String username;
    public int score;
    private Scanner scanner;
    private PrintWriter sender;
    ArrayList<Question> question;
    public ClientHandler(Socket socket ,Server server){
        this.client = socket;
        this.server=server;
        InputStream fromUser = null;
        try {
            fromUser = client.getInputStream();
            scanner = new Scanner(new DataInputStream(fromUser));
            OutputStream toUser = client.getOutputStream();
            sender = new PrintWriter(toUser, true);
            username=receiveMessage();
            score=Server.numberOfUsers;
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
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
            sendMessage("answer");
            if(receiveMessage().equals(question.get(i).answer+""))
                score++;

        }
    }


    //revives message from 1 user and sends it to another
    public void chat(){
        String msg;
        System.out.println("chat");
        String receiver=scanner.nextLine();
        try {
            server.chat("chat",receiver,this);
            server.chat(username ,receiver,this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        do {
            msg=scanner.nextLine();

            try {
                server.chat(msg ,receiver ,this);
            } catch (IOException e) {
            }


        }while (! msg.equals("finish"));
    }



    public void sendMessage(String message){
        try {
            sender.println(message);

        }
        catch (Exception e){
            System.out.println("kos nnt");
        }
    }


    //receiving data from client
    public String receiveMessage(){

        String message;
        message = scanner.nextLine();
        System.out.println("receive:"+message);
        switch (message){
            case "chat":
                chat();
                break;
            case "test":
                test();
                break;
        }
        return message;
    }


    @Override
    public void run() {
        while (true){
            receiveMessage();
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(ClientHandler clientHandler) {
        int compare=(clientHandler.score);
        return compare-this.score;
    }
}
