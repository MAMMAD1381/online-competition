package main;

import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket client;
    ArrayList<Question> question;
    public ClientHandler(Socket socket){
        this.client = socket;
        question=new ArrayList<>();
    }
    public void test(){
        Quiz.getQuestion(question);
        for (int i=0;i< question.size();i++){
            System.out.println(question.get(i).question);
        }
    }
    @Override
    public void run() {
        if(Server.numberOfUsers>=3)
            test();
    }
}
