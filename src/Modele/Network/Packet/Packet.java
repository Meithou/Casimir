package Modele.Network.Packet;

public abstract class Packet {

    private String ipAddress;

    public Packet(String ipAddress){
        this.ipAddress=ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
