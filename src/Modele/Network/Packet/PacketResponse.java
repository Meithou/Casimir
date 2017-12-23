package Modele.Network.Packet;

import Modele.User.User;

import java.util.ArrayList;

public class PacketResponse extends Packet{
    private ArrayList<User> userList;
    private int status;

    public PacketResponse(String ipAddress,int status, ArrayList<User> userList){
        super(ipAddress,4);
        this.status = status;
        this.userList = userList;
    }

    public int getStatus() {
        return status;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
}
