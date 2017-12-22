package Configuration;

public class ConfCasimir {
    private static int[] udp_ports = {5555,6666,4545}; // defining 3 listening udp port in case it's already taken. Will be sending one to many packets to the 3 ports
    private static String IpSendAll = "255.255.255.255"; // defined in case we switch to multicast
    private static int size = 600;

    public static int[] getUdp_ports() {
        return udp_ports;
    }

    public static String getIpSendAll() {
        return IpSendAll;
    }

    public static int getSize() {
        return size;
    }
}
