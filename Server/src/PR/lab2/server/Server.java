package PR.lab2.server;

import PR.lab2.server.common.Connection;
import PR.lab2.server.common.ConnectionImpl;
import PR.lab2.server.common.ConnectionListener;
import PR.lab2.server.common.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.Set;

public class Server implements ConnectionListener {

    private Set<Connection> connections;
    private ServerSocket serverSocket;

    public Server(){
        try {
            serverSocket = new ServerSocket(Connection.Port);

            connections = new LinkedHashSet();
        }
        catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }

    public void start(){
        System.out.println("Server started");
        while (true){
            try {
                Socket socket = serverSocket.accept();
                connections.add(new ConnectionImpl(socket, this));
                socket.getInputStream();
                socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void connectionCreated(Connection c) {
            connections.add(c);
            System.out.println("Connection was added");
    }

    @Override
    public void connectionClosed(Connection c) {
        connections.remove(c);
        c.close();
        System.out.println("Connection was closed");
    }

    @Override
    public void connectionException(Connection c, Exception ex) {
        connectionClosed(c);
        ex.printStackTrace();
    }

    @Override
    public void receivedContent(Message msg) {
        for (Connection c: connections){
                c.Send(msg);
        }
    }
}
