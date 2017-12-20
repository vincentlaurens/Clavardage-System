package connectivite;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_EnvoieMessage {

    public void sendMessageOn(int port, MessageSurLeReseau messageToSend) throws Exception {

        byte[] data;

        data = messageToSend.getBytes();

        DatagramSocket datagramSocket = new DatagramSocket();

        datagramSocket.setBroadcast(true);

        String  broadcastAddress = AdresseIpSysteme.getBroadcastAddress();

        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        datagramPacket.setAddress(InetAddress.getByName(broadcastAddress));
        datagramPacket.setPort(port);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();


    }


}
