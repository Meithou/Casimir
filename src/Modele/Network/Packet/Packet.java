package Modele.Network.Packet;

import java.io.Serializable;

public abstract class Packet implements Serializable{

    private String ipAddress;
    private int type;

    public Packet(String ipAddress,int type){
        this.ipAddress=ipAddress;
        this.type=type;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public int getType(){
        return type;
    }
}
