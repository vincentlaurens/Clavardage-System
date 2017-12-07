package model;

import connectivite.AdresseIpSysteme;

public class UserLocal {

    private String pseudo;
    private String ip;
    private String login;
    private String password;
    private int portTCP;


    public UserLocal(String pseudo, String ip, String login, String password){
        this.pseudo = pseudo;
        this.ip = ip;
        this.login = login;
        this.password = password;
        this.portTCP = AdresseIpSysteme.portReseauToUser();
    }
    public String usePseudoUser(){
        return this.pseudo;

    }
    public String useLoginUser(){
        return this.login;

    }
    public String usePasswordUser(){
        return this.password;

    }
    public String useIpUser(){
        return this.ip;
    }

    public void userPseudoAdd(String pseudo){
        this.pseudo = pseudo;
    }

    public void userIPAdressAdd(String ipAdress) { this.ip = ipAdress;}


    public int usePortTCP(){ return this.portTCP;}

    public UsersDistants retourneUserLocalAsDistant() {


        UsersDistants userLocalAsDistant = new UsersDistants(this.login, this.ip, this.pseudo, this.portTCP);

        return userLocalAsDistant;
    }

}

