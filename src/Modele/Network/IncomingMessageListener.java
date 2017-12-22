package Modele.Network;
import Modele.Network.Packet.*;

public interface IncomingMessageListener {
    void onNewIncomingMessage(Packet p);
}
