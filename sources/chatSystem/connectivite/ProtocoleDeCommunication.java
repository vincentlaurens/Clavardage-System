package connectivite;


import java.io.*;
import java.net.*;

public class ProtocoleDeCommunication {

    private boolean dernier;
    private int numPort;
    private ServerSocket serverSocket;
    private Socket chatSocket;
    private InputStreamReader streamInput;
    private BufferedReader reader;
    private IncomingMessageListener monIncomingMessageListener;


    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {

        serverSocket = new ServerSocket(port);
        monIncomingMessageListener = incomingMessageListener;
        chatSocket = serverSocket.accept();
        streamInput = new InputStreamReader(chatSocket.getInputStream());
        reader = new BufferedReader(streamInput);
        new Thread(() -> run()).start();

    }








    public void ecouteDuReseau(){








    }
}
