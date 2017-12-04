package connectivite;


import main.ClavardageManager;
import model.UsersDistants;

public class ProtocoleDeCommunication {
    private ClavardageManager clavardageManager;

    private TCP_ReceptionMessage tcp_receptionMessage = new TCP_ReceptionMessage();
    private TCP_EnvoieMessage tcp_envoieMessage;

    private UDP_ReceptionMessage udp_receptionMessage = new UDP_ReceptionMessage();
    private UDP_EnvoieMessage udp_envoieMessage;



    public ProtocoleDeCommunication(ClavardageManager theManager){
        this.clavardageManager = theManager;
    }



    public void ecouteDuReseauEnTCP(int port){
        try {
            tcp_receptionMessage.listenOnPort(port, this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ecouteDuReseauEnUDP(int port){
        try {
            udp_receptionMessage.listenOnPort(port, this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void envoieDunMessageEnTCP(String loginDestinaire, MessageSurLeReseau messageToSend){
        UsersDistants usersDestinataire = this.clavardageManager.accesALaListeDesUsagers().retourneUnUtilisateurDistantParSonLogin(loginDestinaire);
        String ipAddress = usersDestinataire.getAdresseIP();
        int portDistant = usersDestinataire.getPortDistant();

        tcp_envoieMessage = new TCP_EnvoieMessage();
        try {
            tcp_envoieMessage.sendMessageOn(ipAddress, portDistant, messageToSend);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void envoieDunMessageEnUDP(String[] loginDestinaire, MessageSurLeReseau messageToSend){
        for (String st: loginDestinaire) {
            UsersDistants usersDestinataire = this.clavardageManager.accesALaListeDesUsagers().retourneUnUtilisateurDistantParSonLogin(st);
            String ipAddress = usersDestinataire.getAdresseIP();
            int portDistant = usersDestinataire.getPortDistant();

            udp_envoieMessage = new UDP_EnvoieMessage();
            try {
                udp_envoieMessage.sendMessageOn(ipAddress, portDistant, messageToSend);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void onNewIncomingMessage(String messageRecue) {
        String[] messageSurLeReseauRecue = messageRecue.split("[$]", 2);
        Entete enteteDuMessageRentrant = Entete.valueOf(messageSurLeReseauRecue[0]);
        Object contenuMessageRentrant = messageSurLeReseauRecue[1];
        MessageSurLeReseau messageSurLeReseau;
        switch (enteteDuMessageRentrant){
            case ENVOIE_MESSAGE:
                //clavardageManager.envoieAuDessus(messageSurLeReseauRecue[1]);
                System.out.println(contenuMessageRentrant);

                break;
            case ENVOIE_USERLOCAL:
                System.out.println(messageSurLeReseauRecue[1].toString());
                break;
        }







    }
}
