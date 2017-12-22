package Modele.Network;

import Modele.Network.Packet.Packet;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMessageSenderService{

    public static void sendMessageOn(String ipAddress, int udp_port, Packet p) throws Exception {
        DatagramSocket senderSocket = new DatagramSocket();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(outputStream);
        os.writeObject(p);
        byte[] data = outputStream.toByteArray();

        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        datagramPacket.setAddress(InetAddress.getByName(ipAddress));
        datagramPacket.setPort(udp_port);

        senderSocket.send(datagramPacket);

        senderSocket.close();


    }
}
