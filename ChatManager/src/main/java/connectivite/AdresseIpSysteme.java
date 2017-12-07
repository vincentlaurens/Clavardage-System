package connectivite;


import main.ChatManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class AdresseIpSysteme {

    private String adresseIPLocale ;



    public String ipInterfaceReseauToUser(ChatManager chatManage) throws IOException {
        InetAddress inetadr = InetAddress.getLocalHost();
        if(inetadr.isLinkLocalAddress()) {
            throw new UnknownHostException("Pas d'adresse Ip exploitable!!! Connectez-vous");

        }
        this.adresseIPLocale = inetadr.getHostAddress();

        chatManage.addIpadressToUser(this.adresseIPLocale);
        return chatManage.userIp();

    }

    public static int portReseauToUser(){
        int localport = 1024;
        boolean bindIsDone = false;

        ServerSocket server = null ;



        while (!bindIsDone){

            try{

                localport++;
                server = new ServerSocket(localport);
                bindIsDone = true ;
                server.close();
            }catch (Exception e){}
        }
        return localport;

    }

}
