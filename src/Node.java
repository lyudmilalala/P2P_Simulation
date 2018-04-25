

import java.util.ArrayList;

class Node {
    private int id;
    private Peer peer;
    private ArrayList<Message>messages;
    private DataFactory dataFactory;
    private RecievingData recievingData;
    public Node(int id) {
        this.id = id;
        peer = new Peer(1234, "225.0.0.0");
        this.messages = new ArrayList<>();
        dataFactory = new DataFactory(id, peer);
        recievingData = new RecievingData(messages, peer);
        Thread recieving = new Thread(recievingData);
        recieving.start();

        Thread producing = new Thread(dataFactory);
        producing.start();


    }

    public static void main(String[] args) throws Exception {
       /* Message message = new Message(1,2,"88");
        System.out.println(message);
        DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.getBytes().length);
        System.out.println(new String(datagramPacket.getData()));*/
        Node node = new Node(2);
        //Node other = new Node(2);
    }

}