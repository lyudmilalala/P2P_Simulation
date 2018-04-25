import java.util.Random;

public class DataFactory implements Runnable {
   private Peer peer;
   private int id;
   private Random random = new Random();

    public DataFactory(int id, Peer peer) {
       this.id = id;
       this.peer = peer;
   }

    @Override
    public void run() {
        System.out.println("Start producing...");
        for (int i = 0; i < 20; i++) {
            Message message  = new Message(this.id, String.valueOf(getData()));
            try {
                peer.sendMessage(message);
                System.out.println("Data has been sent: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private int getData() {
       return random.nextInt(100);
    }
}
