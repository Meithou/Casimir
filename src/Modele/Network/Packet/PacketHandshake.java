package Modele.Network.Packet;

public class PacketHandshake extends Packet {

    private String nickname;

    public PacketHandshake(String ipAddress, String nickname){
        super(ipAddress);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
