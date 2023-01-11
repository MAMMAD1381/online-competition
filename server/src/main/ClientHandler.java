package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private Socket client;
    ArrayList<Question> question;
    public ClientHandler(Socket socket){
        this.client = socket;
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

    public void sendMessage(String message) throws IOException {
        OutputStream toUser = client.getOutputStream();
        PrintWriter sender = new PrintWriter(toUser, true);
        sender.println(message);
    }

    public String receiveMessage() throws IOException {

        String message;
        InputStream fromUser = client.getInputStream();
        Scanner scanner = new Scanner(new DataInputStream(fromUser));
        message = scanner.next();
        System.out.println(message);
        return message;
    }

    @Override
    public void run() {
        if(Server.numberOfUsers>=3)
            test();
    }
}
