package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler extends Thread{
    private Socket client;
    private Server server;
    public String username;
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

    public String receiveMessage() throws IOException {

        String message;
        message = scanner.nextLine();
        System.out.println("recieve:"+message);
        if(message.equals("chat")) {
            chat();
        }
        return message;
    }

    @Override
    public void run() {
        while (true){
            if (Server.numberOfUsers >= 3) {
                test();
                Server.numberOfUsers = 0;
            }
            try {
                receiveMessage();
            } catch (IOException e) {
            }
        }
    }
}
