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
        System.out.println("Start recieving...");
        for (int i = 0; i < 10; i++) {
            Message message = null;
            try {
                message = peer.recieveMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(Collections.frequency(messages, message) ==  0){
                try {
                    peer.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                messages.add(message);
                System.out.println("Data has been received from node: " +message);
            }
        }

    }
}
