package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Runnable{
    private int port;
    private String serverAddress;
    private Socket user;
    private ArrayList<Question> data;

    public User(int port, String serverAddress){
        setPort(port);
        setData(new ArrayList<Question>());
        setServerAddress(serverAddress);
        try{
            setUser( new Socket(getServerAddress(), getPort()));
        }
        catch (Exception e){
            System.out.println("ERROR: "+e.getMessage() +" "+ e.getCause());
        }
        try {
            sendMessage("user1");
            sendMessage("chat");
            sendMessage("user1");
            sendMessage("message");
            System.out.println("hgf");
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

    public void sendMessage(String message) throws IOException {

        OutputStream toServer = getUser().getOutputStream();
        PrintWriter sender = new PrintWriter(toServer, true);
        sender.println(message);
        System.out.println(message);
    }

    public void test(Scanner scanner) throws IOException {
        int i=0;
        while (scanner.hasNext()){
            System.out.println(i);
            String question=scanner.nextLine();
            if(question.equals("finish"))
                break;
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
        InputStream fromServer = getUser().getInputStream();
        Scanner scanner = new Scanner(new DataInputStream(fromServer));

        if (scanner.hasNext()) {
            command=scanner.nextLine();
        }
        System.out.println(command);
        switch (command){
            case "test":
                test(scanner);
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
