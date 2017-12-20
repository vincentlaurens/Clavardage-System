import connectivite.AdresseIpSysteme;
import main.ChatManager;
import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class IpAddressTest {


    @Test
    public void test() {
        AdresseIpSysteme adIP = new AdresseIpSysteme();
        ChatManager chatManager = new ChatManager();

        try {
            String adresseIPTest = adIP.ipInterfaceReseauToUser(chatManager);
            System.out.println(adresseIPTest);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void testBroadcast(){

        AdresseIpSysteme aip = new AdresseIpSysteme();

        aip.getBroadcastAddress();
    }




}

