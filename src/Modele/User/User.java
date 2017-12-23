package Modele.User;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import Modele.Messages.*;

import java.net.InetAddress;

public class User {
    private String ipAddress;
    private String nickname;
    private int selectedPort;

    private HashMap<String,ArrayList<Message>> conversation;

    public User (String nickname, String ipAddress){

        this.nickname = nickname;
        this.conversation = new HashMap<>();

        this.ipAddress = ipAddress;
}

    public User(){
        try {
            this.ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.nickname = null;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSelectedPort() {
        return selectedPort;
    }

    public void setSelectedPort(int selectedPort) {
        this.selectedPort = selectedPort;
    }
}