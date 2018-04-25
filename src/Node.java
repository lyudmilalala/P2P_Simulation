

import java.util.ArrayList;

class Node {
    private int id;
    private Peer peer;
    private ArrayList<Message>messages;
    private DataFactory dataFactory;
    private RecievingData recievingData;
    private SavingData savingData;
    private int dataSize;

    private Node(int id, int dataSize) {
        this.id = id;
        this.dataSize = dataSize;
        peer = new Peer(1234, "225.0.0.0");
        this.messages = new ArrayList<>();
        dataFactory = new DataFactory(id, peer);
        recievingData = new RecievingData(messages, peer, dataSize);
        savingData = new SavingData(id, messages);

        Thread saving = new Thread(savingData);
        Thread recieving = new Thread(recievingData);
        Thread producing = new Thread(dataFactory);

        producing.start();
        recieving.start();
        try {
            recieving.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saving.start();
        if(!recieving.isAlive()) {
            producing.interrupt();
        }

    }
    public static void main(String[] args) {
        int dataSize = Integer.parseInt(args[0]);
       Thread t1 = new Thread(new Runnable() {
           @Override
           public void run() {
               Node node1  = new Node(1, dataSize);
           }
       });
       Thread t2 = new Thread(new Runnable() {
           @Override
           public void run() {
               Node node2 = new Node(2, dataSize);
           }
       });
       Thread t3 = new Thread(new Runnable() {
           @Override
           public void run() {
               Node node3 = new Node(3, dataSize);
           }
       });
       t1.start();
       t2.start();
       t3.start();
    }

}