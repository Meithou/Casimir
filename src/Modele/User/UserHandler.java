package Modele.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserHandler {
    HashMap<String,User> ActiveUser;

    public UserHandler(){
        ActiveUser = new HashMap<>();
    }

    public ArrayList<User> getUserList(){
        ArrayList<User> result = new ArrayList<>();
        for (User user : ActiveUser.values()){
            result.add(user);
        }
        return result;
    }

    public ArrayList<String> getNameList(){
        ArrayList<String> result = new ArrayList<>();
        for (User user : ActiveUser.values()){
            result.add(user.getNickname());
        }
        return result;
    }

    public int addUser(User u){
        if(ActiveUser.containsKey(u.getIpAddress()))
            return 42; // error code
        if(getNameList().contains(u.getNickname()))
            return 7; // error code
        ActiveUser.put(u.getIpAddress(),u);
        return 0; // normal use
    }

    public boolean removeUser(User u){
        return ActiveUser.remove(u.getIpAddress(),u);
    }
    public boolean removeUser(String ipAddress){
        return ActiveUser.remove(ipAddress) != null;
    }

    public int changeName(String ipAddress,String name){
        if(getNameList().contains(name))
            return 7; // error code
        if(!ActiveUser.containsKey(ipAddress))
            return 42; // user error
        ActiveUser.get(ipAddress).setNickname(name);
        return 0;
    }
    public String getIp(String username){
        for(User user : ActiveUser.values())
            if(user.getNickname()==username)
                return user.getIpAddress();
        return null;
    }
    public User getUserFromName(String nickname){
        for(User u : ActiveUser.values())
            if(u.getNickname()==nickname)
                return u;
        return null;
    }
}
