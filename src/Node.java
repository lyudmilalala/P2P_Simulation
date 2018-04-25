

import java.util.ArrayList;

class Node {
    private int id;
    private Peer peer;
    private ArrayList<Message>messages;
    private DataFactory dataFactory;
    private RecievingData recievingData;
    private SavingData savingData;

    public Node(int id) {
        this.id = id;
        peer = new Peer(1234, "225.0.0.0");
        this.messages = new ArrayList<>();
        dataFactory = new DataFactory(id, peer);
        recievingData = new RecievingData(messages, peer);
        savingData = new SavingData(messages);

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

    }

    public static void main(String[] args) {
       /* Message message = new Message(1,2,"88");
        System.out.println(message);
        DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.getBytes().length);
        System.out.println(new String(datagramPacket.getData()));*/
        Node node = new Node(2);
        //Node node1 = new Node(3);
        //Node other = new Node(2);
    }

}