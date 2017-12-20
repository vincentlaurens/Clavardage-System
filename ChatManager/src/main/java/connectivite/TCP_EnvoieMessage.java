package connectivite;


import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCP_EnvoieMessage{
    private Socket chatSocket;
    private PrintWriter writer;


    public void sendMessageOn(String ipAddress, int port, MessageSurLeReseau theMessageToSend) throws Exception {

        chatSocket = new Socket(InetAddress.getByName(ipAddress), port);
        writer = new PrintWriter(chatSocket.getOutputStream());
        writer.println(theMessageToSend);
        writer.close();


    }
}
