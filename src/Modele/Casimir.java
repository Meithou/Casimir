package Modele;

import Controller.Controller;
import Modele.Messages.MessageHandler;
import Modele.Network.PacketHandler;
import Modele.User.*;

public class Casimir {
    private Controller controller;
    private UserHandler userHandler;
    private PacketHandler packetHandler;
    private MessageHandler messageHandler;

    public void launch(){
        controller = new Controller();
        userHandler = new UserHandler();
        messageHandler = new MessageHandler();
        packetHandler = new PacketHandler(this); // giving himself for anchor

        // ihm stuff


    }

    public void userLeave(String ipAddress){
        messageHandler.end_conversation(ipAddress);
        userHandler.removeUser(ipAddress);
        controller.userLeave(ipAddress);
        // Inform Controller here
    }
    public void userArrive(User user){
        int retour = userHandler.addUser(user);
    }

}
