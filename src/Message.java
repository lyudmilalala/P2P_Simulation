import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {
    private int sender;
    private String content;
    public Message(int sender, String content) {
        this.sender = sender;
        this.content = content;
    }
    public Message(String content) {
        this.sender = Integer.parseInt(content.substring(0, content.indexOf(" ")));
        this.content = content.substring(content.indexOf(" ")+1);
    }


    public int getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public byte[] getBytes() {
        return this.toString().getBytes();
    }

    @Override
    public String toString() {
        return sender + " " + content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return sender == message.sender &&
                Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sender, content);
    }
}

