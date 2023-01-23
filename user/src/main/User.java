package main;

import UIAndControllers.Person;
import UIAndControllers.ScoreBoard;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Runnable{
    private int port;
    private String serverAddress;
    private Socket user;
    private PrintWriter sender;
    private Scanner scanner;
    private ArrayList<Question> data;
    ArrayList<String> friends;

    public User(int port, String serverAddress){
        setPort(port);
        setData(new ArrayList<Question>());
        setServerAddress(serverAddress);
        try{
            setUser( new Socket(getServerAddress(), getPort()));
            OutputStream toServer = getUser().getOutputStream();
            sender = new PrintWriter(toServer, true);
            InputStream fromServer = getUser().getInputStream();
            scanner = new Scanner(new DataInputStream(fromServer));
        }
        catch (Exception e){
            System.out.println("ERROR: "+e.getMessage() +" "+ e.getCause());
        }
        sendMessage("user");
    }

    private int getPort() {
        return port;
    }

    private void setPort(int port) {
        this.port = port;
    }

    private String getServerAddress() {
        return serverAddress;
    }

    private void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    private Socket getUser() {
        return user;
    }

    private void setUser(Socket user) {
        this.user = user;
    }

    public void sendPM(String username ,String msg){
        sendMessage("chat");
        sendMessage(username);
        sendMessage(msg);
        sendMessage("finish");
    }

    public void receivePM(){
        String sender=scanner.nextLine();
        System.out.println("pm");
        String msg="";

        while (! msg.equals("finish")){
            msg=scanner.nextLine();
            System.out.println(sender+": "+msg);
        }
    }

    public void sendMessage(String message) {


        sender.println(message);
    }



    // gets questions from server and stores it in data arraylist
    public void test() {
        int i=0;
        while (scanner.hasNext()){
            String question=scanner.nextLine();
            if(question.equals("finish")) {
                System.out.println("finish");
                break;
            }
            String[] option=new String[4];
            option[0]=scanner.nextLine();
            option[1]=scanner.nextLine();
            option[2]=scanner.nextLine();
            option[3]=scanner.nextLine();



            data.add(new Question(question,option,0));
            i++;

        }


    }
    public  String receiveMessage() {

        String command = null;



        command=scanner.nextLine();
        System.out.println("command:"+command);
        switch (command){
            case "test":
                test();
                break;
            case "chat":
                System.out.println("chattt");
                receivePM();
                break;
            case "scoreBoard":
                scoreBoard();
                break;
            case "Friends":
                getFriendList();
            default:
                return command;

        }
        return command;
    }

    private void getFriendList() {
        String msg=null;
        while (!msg.equals("finish")){
            msg=scanner.nextLine();
            friends.add(msg);
        }
    }

    private void scoreBoard() {
        ArrayList<Person> list=new ArrayList<>();
        String name="";
        while (true){
            if (name.equals("finishBoard")) {
                ScoreBoard.setList(list);
                System.out.println("finish "+name);
                break;
            }
            name=scanner.nextLine();
            Integer score=Integer.parseInt(scanner.nextLine());
            list.add(new Person(name,score));
            System.out.println("list:"+name+"  score:"+score);


        }

    }

    public ArrayList<Question> getData() {
        return data;
    }

    public void setData(ArrayList<Question> data) {
        this.data = data;
    }

    @Override
    public void run() {

        while (true){
            receiveMessage();

        }
    }
}
