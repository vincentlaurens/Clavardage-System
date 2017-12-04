package connectivite;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDP_ReceptionMessage implements Runnable {


    private DatagramPacket datagramPacket;
    private DatagramSocket datagramSocket;
    private ProtocoleDeCommunication monProtocoleDeCom;


    public void listenOnPort(int port, ProtocoleDeCommunication protocoleDeCommunication) throws Exception {

        datagramSocket = new DatagramSocket(port);
        datagramPacket = new DatagramPacket(new byte[500], 500);
        monProtocoleDeCom = protocoleDeCommunication;

        new Thread(() -> run()).start();



    }

    @Override
    public void run() {
        boolean uneVariablaAtrue = true;
        while (uneVariablaAtrue) {
            try {
                datagramSocket.receive(datagramPacket);
                String message = new String(datagramPacket.getData(), datagramPacket.getOffset(), datagramPacket.getLength());
                monProtocoleDeCom.onNewIncomingMessage(message);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        datagramSocket.close();
    }
}
