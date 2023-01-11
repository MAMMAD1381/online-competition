package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main {
    private int port = 9090;
    public static void main(String[] args) throws IOException {

        Thread gui=new Thread(new ServerGUI());
        gui.start();
        Thread server=new Thread(new Server(9090));
        server.start();

    }



}

