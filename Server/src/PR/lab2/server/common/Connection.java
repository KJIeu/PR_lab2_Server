package PR.lab2.server.common;

public interface Connection {
    public static final int Port = 8989;

    public void Send(Message msg);
    public void close();
}
