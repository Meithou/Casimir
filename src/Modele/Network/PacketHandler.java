package Modele.Network;

import Modele.Casimir;
import Modele.Messages.MessageHandler;
import Modele.Network.Packet.Packet;
import Configuration.ConfCasimir;
import Modele.Network.Packet.PacketHandshake;
import Modele.User.User;
import Modele.User.UserHandler;

import java.net.SocketException;

public class PacketHandler {
    private Casimir casimir; // anchor
    UDPMessageReceiverService receiverService;
    private int selectedPort;

    public PacketHandler(){

        selectedPort= 0;
        while(selectedPort<ConfCasimir.getUdp_ports().length) {
            try {
                receiverService = new UDPMessageReceiverService(ConfCasimir.getUdp_ports()[selectedPort], ConfCasimir.getSize());
            } catch (SocketException e) {
               selectedPort++;
            }
        }
    }
    public PacketHandler(Casimir casimir){
        this.casimir = casimir;
        selectedPort= 0;
        while(selectedPort<ConfCasimir.getUdp_ports().length) {
            try {
                receiverService = new UDPMessageReceiverService(ConfCasimir.getUdp_ports()[selectedPort], ConfCasimir.getSize());
            } catch (SocketException e) {
                selectedPort++;
            }
        }
    }

    public void packetProcess(Packet p) throws Exception {

        switch(p.getClass().getName()){

            case "PacketMessage":

                break;

            case "PacketAskNickname":

                break;

            case "PacketHandshake":
                PacketHandshake ph = (PacketHandshake) p;
                User user = new User(ph.getNickname(),ph.getIpAddress());
                casimir.userArrive(user);
                break;

            case "PacketLeave":
                casimir.userLeave(p.getIpAddress());
                break;

            default:

                throw new Exception();


        }


    }

}
