package connectivite;


import main.ChatManager;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class AdresseIpSysteme {

    private String adresseIPLocale ;



    public String ipInterfaceReseauToUser(ChatManager chatManage) throws IOException {
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (interfaces.hasMoreElements()) {
            NetworkInterface currentInterface = interfaces.nextElement();
            Enumeration<InetAddress> addresses = currentInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress currentAddress = addresses.nextElement();
                if(currentAddress.isSiteLocalAddress()){
                    adresseIPLocale = currentAddress.getHostAddress();

                }
            }
        }

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
