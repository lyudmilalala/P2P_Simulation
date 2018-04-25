import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Peer {
    private int port;
    private InetAddress group;
    private MulticastSocket socket;
    private DatagramPacket datagramPacket;
    public Peer(int port, String group) {
        try {
            this.port = port;
            this.group = InetAddress.getByName(group);
            this.socket = new MulticastSocket(port);
            this.socket.setTimeToLive(100);
            this.socket.joinGroup(this.group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean sendMessage(Message message) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(message);
        byte[] data = byteArrayOutputStream.toByteArray();
        socket.send(new DatagramPacket(data, data.length, group, port));
        return true;
    }
    public Message recieveMessage() throws Exception {
        byte[] buffer = new byte[1024*4];
        socket.receive(new DatagramPacket(buffer, buffer.length, group, port));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Message message = (Message)objectInputStream.readObject();
        return message;
    }



}
