package Modele.Network.Packet;

public class PacketHandshake extends Packet {

    private String nickname;
    private int selectedPort;

    public PacketHandshake(String ipAddress, String nickname,int selectedPort){
        super(ipAddress,1);
        this.nickname = nickname;
        this.selectedPort = selectedPort;
    }

    public String getNickname() {
        return nickname;
    }
}
