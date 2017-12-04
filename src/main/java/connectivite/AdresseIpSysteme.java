package connectivite;


import main.ChatManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AdresseIpSysteme {

    private String adresseIPLocale ;
    private ChatManager monChatManager;

    public String IpInterfaceReseauToUser() throws IOException {
        InetAddress inetadr = InetAddress.getLocalHost();
            if(inetadr.isLinkLocalAddress()) {
                throw new UnknownHostException("Pas d'adresse Ip exploitable!!! Connectez-vous");

            }
                this.adresseIPLocale = inetadr.getHostAddress();
                this.monChatManager = new ChatManager();
                this.monChatManager.addIpadressToUser(this.adresseIPLocale);
                return this.monChatManager.userIp();

    }
}

