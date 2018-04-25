import java.util.ArrayList;
import java.util.Collections;

public class RecievingData implements Runnable {
    private ArrayList<Message> messages;
    private Peer peer;
    public RecievingData(ArrayList<Message> messages, Peer peer) {
        this.messages = messages;
        this.peer = peer;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; ) {
            Message message = null;
            try {
                message = peer.recieveMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(Collections.frequency(messages, message) ==  0){
                messages.add(message);
                System.out.println("Data has been recieved from node: " + message.getSender());
                System.out.println("All data till now: ");
                System.out.println(message);
            }
        }

    }
}
