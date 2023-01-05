import java.net.ServerSocket;

public class Server{
    private int port;
    private ServerSocket server;

    public Server(int port){
        setPort(port);
        try {
            setServer(new ServerSocket(getPort()));
        }
        catch (Exception e){
            System.out.println(e.getMessage() + " " + e.getCause());
        }
    }

    private int getPort() {
        return port;
    }

    private void setPort(int port) {
        this.port = port;
    }

    private ServerSocket getServer() {
        return server;
    }

    private void setServer(ServerSocket server) {
        this.server = server;
    }
}
