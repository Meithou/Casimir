package Modele.User;

import java.util.ArrayList;
import java.util.HashMap;

import Modele.Messages.*;

public class User {
    private String ipAddress;
    private String nickname;

    private HashMap<String,ArrayList<Message>> history;

    public User (String nickname, String ipAddress){

        this.nickname = nickname;
        this.history = new HashMap<>();

        this.ipAddress = ipAddress;
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
}