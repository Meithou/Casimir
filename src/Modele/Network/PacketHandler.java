package Modele.Network;

import Modele.Casimir;
import Modele.Messages.Message;
import Modele.Network.Packet.*;
import Configuration.ConfCasimir;
import Modele.User.User;
import java.util.Timer;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.TimerTask;

public class PacketHandler {
    private Casimir casimir; // anchor
    UDPMessageReceiverService receiverService;
    private int selectedPort;
    Timer timer ;
    public PacketHandler(Casimir casimir){
        this.casimir = casimir;
        selectedPort= 0;
        timer = new Timer();
        while(selectedPort<ConfCasimir.getUdp_ports().length) {
            try {
                receiverService = new UDPMessageReceiverService(ConfCasimir.getUdp_ports()[selectedPort], ConfCasimir.getSize(),this);
                receiverService.start();
                return;
            } catch (SocketException e) {
                selectedPort++;
            }
        }
      }

    public int getSelectedPort(){return selectedPort;}
    public void login(String nickname) throws Exception {
        PacketHandshake ph = new PacketHandshake(casimir.getIp(),nickname,selectedPort);
        UDPMessageSenderService.sendAll(ph);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(!casimir.getLogged()) {
                    casimir.loginAnswer(1, new ArrayList<User>());
                }
            }
        }, 5*1000);
    }
    public void logout() throws Exception {
        UDPMessageSenderService.sendAll(new PacketLeave(casimir.getIp()));
        casimir.setLogged(false);
    }
    public void messageSend(User user,Message message) throws Exception {
        PacketMessage pm = new PacketMessage(casimir.getIp(),message);
        UDPMessageSenderService.sendMessageOn(user.getIpAddress(),ConfCasimir.getUdp_ports()[user.getSelectedPort()],pm);
    }

    public void userAccept(User user, ArrayList<User> userList){
        PacketResponse pr = new PacketResponse(user.getIpAddress(),1,userList);
        try {
            UDPMessageSenderService.sendMessageOn(user.getIpAddress(),ConfCasimir.getUdp_ports()[user.getSelectedPort()],pr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void userRefuse(User user, int status,ArrayList<User> userList){
        PacketResponse pr = new PacketResponse(user.getIpAddress(),status,userList);
        try {
            UDPMessageSenderService.sendMessageOn(user.getIpAddress(),ConfCasimir.getUdp_ports()[user.getSelectedPort()],pr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void packetProcess(Packet p) throws Exception {

        switch(p.getType()){

            case 3:
                if(casimir.getLogged()) {
                    PacketMessage pm = (PacketMessage) p;
                    casimir.messageReceive(pm.getIpAddress(), pm.getMessage());
                }
                break;

            case 4:
                if(!casimir.getLogged()) {
                    PacketResponse pr = (PacketResponse) p;
                    casimir.loginAnswer(pr.getStatus(), pr.getUserList());
                }
                break;

            case 1:
                if(casimir.getLogged()) {
                    PacketHandshake ph = (PacketHandshake) p;
                    User user = new User(ph.getNickname(), ph.getIpAddress());
                    casimir.userArrive(user);
                }
                break;

            case 2:
                if(casimir.getLogged())
                    casimir.userLeave(p.getIpAddress());
                break;

            default:
                throw new Exception();
        }
    }

}
