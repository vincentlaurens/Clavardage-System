package connectivite;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_EnvoieMessage {

    public void sendMessageOn(String ipAddress, int port, MessageSurLeReseau messageToSend) throws Exception {

        byte[] data;

        data = messageToSend.getBytes();

        DatagramSocket datagramSocket = new DatagramSocket();

        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        datagramPacket.setAddress(InetAddress.getByName(ipAddress));
        datagramPacket.setPort(port);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();


    }


}
