import java.io.*;
import java.net.Socket;
public class User {
    private int port;
    private String serverAddress;
    private Socket user;

    public User(int port, String serverAddress){
        setPort(port);
        setServerAddress(serverAddress);
        try{
            setUser( new Socket(getServerAddress(), getPort()));
        }
        catch (Exception e){
            System.out.println("ERROR: "+e.getMessage() +" "+ e.getCause());
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

        OutputStream outputToServer = getUser().getOutputStream();
        PrintWriter sender = new PrintWriter(outputToServer);
        sender.println(message);
    }

    public String receiveMessage() throws IOException {

        String message;
        InputStream inputFromServer = getUser().getInputStream();
        DataInputStream receiver = new DataInputStream(inputFromServer);
        message = receiver.readLine();

        return message;
    }
}
