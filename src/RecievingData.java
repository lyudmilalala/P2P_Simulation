import java.util.ArrayList;
import java.util.Collections;

public class RecievingData implements Runnable {
    private ArrayList<Message> messages;
    private Peer peer;
    private int dataSize;
    RecievingData(ArrayList<Message> messages, Peer peer, int dataSize) {
        this.messages = messages;
        this.peer = peer;
        this.dataSize = dataSize;

    }
    @Override
    public void run() {
        System.out.println("Start recieving...");
        while(messages.size() < dataSize) {
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
