package Modele;

import Controller.Controller;
import IHM.IHMHandler;
import IHM.Login;
import Modele.Messages.Message;
import Modele.Messages.MessageHandler;
import Modele.Network.PacketHandler;
import Modele.User.*;

import javax.swing.*;
import java.util.ArrayList;

public class Casimir {
    private Controller controller;
    private UserHandler userHandler;
    private PacketHandler packetHandler;
    private MessageHandler messageHandler;
    private User user;
    private String pendingName;
    private boolean logged;

    public Casimir(){
        controller = new Controller(this);
        userHandler = new UserHandler();
        messageHandler = new MessageHandler();
        packetHandler = new PacketHandler(this); // giving himself for anchor
        user = new User();
        logged = false;
        // ihm stuff


    }

    public void launch(){
        controller.start_ihm();
    }
    public void login(String nickname){
        try {
            packetHandler.login(nickname);
            pendingName = nickname;
        } catch (Exception e) {
            e.printStackTrace();
            controller.loginError();
        }
        //Del late
        loginSuccess();
    }
    public void loginSuccess(){
        controller.loginSuccess(pendingName);
        user.setNickname(pendingName);
        user.setSelectedPort(packetHandler.getSelectedPort());
    }
    public void loginAnswer(int status, ArrayList<User> userList){
        if(status ==1)
            loginSuccess();
        else
            controller.loginError();
    }
    public void userLeave(String ipAddress){
        messageHandler.end_conversation(ipAddress);
        userHandler.removeUser(ipAddress);
        controller.userLeave(ipAddress);
        // Inform Controller here
    }
    public void userArrive(User user){
        int result = userHandler.addUser(user);
        if(result==0) {
            controller.userArrive(user.getNickname());
            packetHandler.userAccept(user, userHandler.getUserList());
        }
        else
            packetHandler.userRefuse(user,result,userHandler.getUserList());
    }
    public void messageReceive(String ipAddress,Message message){
        messageHandler.addMessage(ipAddress,message);
        controller.messageReceive(message);
    }

    public void messageSend(String nickname,String message){
        Message builtmessage = new Message(message,user);
        User userDest  = userHandler.getUserFromName(nickname);
        try {
            packetHandler.messageSend(userDest,builtmessage);
            messageHandler.addMessage(userDest.getIpAddress(),builtmessage);
            controller.messageReceive(builtmessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout(){
        try {
            packetHandler.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getIp(){
        return user.getIpAddress();
    }
    public boolean getLogged(){
        return logged;
    }
    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
