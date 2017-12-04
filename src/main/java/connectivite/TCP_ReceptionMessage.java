package connectivite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_ReceptionMessage implements Runnable{

    private ServerSocket serverSocket;
    private Socket chatSocket;
    private InputStreamReader streamInput;
    private BufferedReader reader;
    private ProtocoleDeCommunication monProtocoleDeCom;


    public void listenOnPort(int port, ProtocoleDeCommunication leProto) throws Exception {

        serverSocket = new ServerSocket(port);
        monProtocoleDeCom = leProto;
        chatSocket = serverSocket.accept();
        streamInput = new InputStreamReader(chatSocket.getInputStream());
        reader = new BufferedReader(streamInput);
        new Thread(() -> run()).start();

    }

    @Override
    public void run() {
        boolean varTrue = true;
        while (varTrue){
            try{
                String messageRecue = reader.readLine();
                if( messageRecue != null) {
                    monProtocoleDeCom.onNewIncomingMessage(messageRecue);


                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
