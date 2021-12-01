package PR.lab2.server.common;

public interface ConnectionListener {

    public void connectionCreated(Connection c);
    public void connectionClosed(Connection c);
    public void connectionException(Connection c, Exception ex);
    public void receivedContent(Message msg);
}
