package Modele.Network.Packet;

import Modele.Messages.Message;

public class PacketMessage extends Packet {

    private Message message;

    public PacketMessage(String ipAdress, Message message){
        super(ipAdress);
        this.message=message;
    }

    public Message getMessage() {
        return message;
    }
}
