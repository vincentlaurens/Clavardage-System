package model;

public class UsersDistants {

    private String login;
    private String adresseIP;
    private String pseudoActuel;
    private int portDistant;




    public UsersDistants(String leLogin, String lAdresseIP, String lePseudo, int lePortDistant){
        this.login = leLogin;
        this.adresseIP = lAdresseIP;
        this.pseudoActuel = lePseudo;
        this.portDistant = lePortDistant;
    }

    public String toString(){
        String userDistantsEnString = login +" "+ adresseIP+" "+ pseudoActuel+" "+portDistant;
        return userDistantsEnString;

    }
}
