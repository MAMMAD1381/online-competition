package main;

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
        try {
            sendMessage("user1");
            sendPM("user1","hi");
        } catch (IOException e) {

        }
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
        try {
            sendMessage("chat");
            sendMessage(username);
            sendMessage(msg);
            sendMessage("finish");
        } catch (IOException e) {

        }
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

    public void sendMessage(String message) throws IOException {


        sender.println(message);
    }


    public void test(Scanner scanner) throws IOException {
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
            long answer= Long.parseLong(scanner.nextLine());
            getData().add(new Question(question,option,answer));


            System.out.println(getData().get(i).question);
            i++;

        }


    }
    public  void receiveMessage() throws IOException {

        String command = null;



        command=scanner.nextLine();
        System.out.println("command:"+command);
        switch (command){
            case "test":
                test(scanner);
                break;
            case "chat":
                System.out.println("chattt");
                receivePM();
                break;
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

            try {
                receiveMessage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
}
