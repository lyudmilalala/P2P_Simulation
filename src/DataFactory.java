import java.util.Random;

public class DataFactory implements Runnable {
   private Peer peer;
   private int id;
   private Random random = new Random();

    DataFactory(int id, Peer peer) {
       this.id = id;
       this.peer = peer;
   }

    @Override
    public void run() {
        System.out.println("Start producing...");
        while(!Thread.currentThread().isInterrupted()) {
            Message message  = new Message(this.id, String.valueOf(getData()));
            try {
                peer.sendMessage(message);
                System.out.println("Data has been sent: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private int getData() {
       return random.nextInt(1000);
    }
}
