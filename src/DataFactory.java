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
        for (int i = 0; i < 10; i++) {
            Message message  = new Message(this.id, String.valueOf(getData()));
            try {
                peer.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private int getData() {
       return random.nextInt(100);
    }
}
