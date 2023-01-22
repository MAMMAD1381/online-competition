package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler  extends Thread implements Comparable{
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
        } catch (IOException e) {

        }
        question=new ArrayList<>();
    }


    //function to send the questions to clients
    public void test(){
        Quiz.getQuestion(question);
        try {
            sendMessage("test");
            for (int i=0;i< question.size();i++){
                sendMessage(question.get(i).question);
                sendMessage(question.get(i).options[0]);
                sendMessage(question.get(i).options[1]);
                sendMessage(question.get(i).options[2]);
                sendMessage(question.get(i).options[3]);
                sendMessage(Long.toString(question.get(i).answer));

            }
            sendMessage("finish");

        } catch (IOException e) {
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



    public void sendMessage(String message) throws IOException {

        sender.println(message);
    }


    //receiving data from client
    public String receiveMessage() throws IOException {

        String message;
        message = scanner.nextLine();
        System.out.println("recieve:"+message);
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
            try {
                receiveMessage();
            } catch (IOException e) {
            }
        }
    }



    @Override
    public int compareTo(Object o) {
        int compare=((ClientHandler) o).score;
        return this.score-compare;
    }
}
