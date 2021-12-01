package PR.lab2.server.common;

import java.io.*;
import java.net.Socket;

public class ConnectionImpl implements Connection, Runnable{

    private Socket socket;
    private ConnectionListener connectionListener;
    private boolean needToRun = true;
    private OutputStream out;


    public ConnectionImpl(Socket socket, ConnectionListener connectionListener){
        socket = this.socket;
        connectionListener = this.connectionListener;
        Thread thread = new Thread();
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    @Override
    public void Send(Message msg) {
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        needToRun = false;
    }

    @Override
    public void run() {
        while (needToRun){
            try {
                InputStream in = socket.getInputStream();
                int amount = in.available();
                if (amount != 0){
                    ObjectInputStream objIn = new ObjectInputStream(in);
                    Message msg = (Message) objIn.readObject();
                    connectionListener.receivedContent(msg);
                } else {
                    Thread.sleep(100);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
