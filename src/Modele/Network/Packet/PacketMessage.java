package Modele.Network.Packet;

import Modele.Messages.Message;

public class PacketMessage extends Packet {

    private Message message;

    public PacketMessage(String ipAddress, Message message){
        super(ipAddress,3);
        this.message=message;
    }

    public Message getMessage() {
        return message;
    }
}
