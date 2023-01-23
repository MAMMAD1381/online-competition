package main;

import UIAndControllers.MainMenu;
import UIAndControllers.Person;
import UIAndControllers.ScoreBoard;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Thread{
    private int port;
    private String serverAddress;
    private Socket user;
    private PrintWriter sender;
    private Scanner scanner;
    private ArrayList<Question> data;
    ArrayList<String> friends;
    private ScoreBoard scoreBoard;

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
        String msg="";
        System.out.println("pm");
        while (true){
            msg=scanner.nextLine();
            MainMenu.updateChat(sender,msg);
            System.out.println("pm:"+msg);
            if (msg.equals("finish"))
                break;
            System.out.println(sender+": "+msg);
        }

        MainMenu.updateChat(sender,msg);
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
        switch (command){
            case "test":
                test();
                break;
            case "chat":
                receivePM();
                break;
            case "scoreBoard":
                scoreBoard();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        scoreBoard = new ScoreBoard();
                        scoreBoard.show();
                        PauseTransition delay = new PauseTransition(Duration.seconds(1));
                        delay.setOnFinished( event -> scoreBoard.close() );
                        delay.play();
                    }
                });

                break;
            case "Friends":
                getFriendList();
            default:
                return command;

        }
        return command;
    }

    private void getFriendList() {
        String msg="";
        friends=new ArrayList<>();
        while (true){
            msg=scanner.nextLine();
            if(msg.equals("finish"))
                break;
            friends.add(msg);
        }
    }

    private void scoreBoard() {
        ArrayList<Person> list=new ArrayList<>();
        String name="";
        int i=0;
        while (true){

            name=scanner.nextLine();
            if (name.equals("finishBoard")) {
                ScoreBoard.setList(list);
                return;
            }
            Integer score=Integer.parseInt(scanner.nextLine());
            list.add(new Person(name,score));




        }


    }

    public ArrayList<Question> getData() {
        return data;
    }

    public void setData(ArrayList<Question> data) {
        this.data = data;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    @Override
    public void run() {

        while (true){
            if(scanner.hasNext())
                receiveMessage();


        }
    }
}
