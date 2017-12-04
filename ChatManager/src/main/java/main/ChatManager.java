package main;

import connectivite.ProtocoleDeCommunication;
import model.ListeDesUsagers;
import model.Sessions;
import model.UserLocal;

public class ChatManager {
    private ProtocoleDeCommunication protocoleDeCommunication = new ProtocoleDeCommunication(this);
    private final ListeDesUsagers listeDesUsagers = new ListeDesUsagers(this);
    private final Sessions session;
    private UserLocal user;


    public ChatManager(){
        this.session = new Sessions();
        this.user = new UserLocal(null, null, "vince", "toto");
    }

    public ListeDesUsagers accesALaListeDesUsagers(){
        return listeDesUsagers;
    }

    public String userLogin(){
        return  this.user.useLoginUser();
    }
    public String userPassword(){
        return this.user.usePasswordUser();
    }
    public void addPseudoToUser(String pseudo){
        this.user.userPseudoadd(pseudo);
    }
    public String userPseudo(){
        return this.user.usePseudoUser();
    }

}







