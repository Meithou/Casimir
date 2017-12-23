package Modele.Network;

import Modele.Network.Packet.*;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPMessageReceiverService extends Thread implements IncomingMessageListener {

    private int size;
    private PacketHandler packetHandler;
    private DatagramSocket receiverSocket;
    private DatagramPacket receivedPacket;

    public UDPMessageReceiverService(int udp_port, int size,PacketHandler packetHandler) throws SocketException {

        this.size=size; // 500 de base
        this.packetHandler=packetHandler;
        receiverSocket = new DatagramSocket(udp_port);
        receivedPacket = new DatagramPacket(new byte[size], size);

    }

    @Override
    public void run() {

        while(receiverSocket != null) {

            try {
                receiverSocket.receive(receivedPacket);
                byte[] data = receivedPacket.getData();

                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                Packet p = (Packet) is.readObject();

                onNewIncomingMessage(p);

            } catch (Exception e){
            }

        }

    }

    public void close() {
        try {
            receiverSocket.close();
            receiverSocket = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNewIncomingMessage(Packet p) {
        try {
            packetHandler.packetProcess(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
