package PR.lab2.server.common;

public class MessageImpl implements Message{

    private final String nick;
    private final String content;
    private final int type;

    public MessageImpl(String nick, String content, int type){
        this.nick = nick;
        this.content = content;
        this.type = type;

    }
    @Override
    public String getNick() {
        return nick;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public int getType() {
        return type;
    }
}
