package model;

import main.ChatManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ListeDesUsagers {

    private ChatManager clavardageManager;

    private static HashMap<String, UsersDistants> listeDesUsersParLeurLogin = new HashMap<String, UsersDistants>();

    public ListeDesUsagers(ChatManager theManager){
        this.clavardageManager = theManager;

    }

    public void ajouteUnUtilisateurDistantALaListe(UsersDistants nouveauUserDistant){
        String leLoginUserDistant = nouveauUserDistant.getLogin();
        if(listeDesUsersParLeurLogin.containsKey(leLoginUserDistant)){
            listeDesUsersParLeurLogin.replace(leLoginUserDistant, nouveauUserDistant);
        }
        else{
            listeDesUsersParLeurLogin.put(leLoginUserDistant, nouveauUserDistant);
        }
    }

    public UsersDistants retourneUnUtilisateurDistantParSonLogin(String login){
        UsersDistants leUser = listeDesUsersParLeurLogin.get(login);
        return leUser;
    }

    public Set<String> retourneToutLesUsagers() {
        Set<String> toutLesKeyDeLaTableDesUsers = listeDesUsersParLeurLogin.keySet();
        return toutLesKeyDeLaTableDesUsers;


    }

    public String retourneToutLesUsagersAsString(){
        String toutLesUsagersConnusAsString = "";
        Set<String> toutlesKeyDeLaTableDesUsers = retourneToutLesUsagers();
        for (String st: toutlesKeyDeLaTableDesUsers) {
            toutLesUsagersConnusAsString = toutLesUsagersConnusAsString.concat(st.toString());
            toutLesUsagersConnusAsString = toutLesUsagersConnusAsString.concat("//");
        }
        return  toutLesUsagersConnusAsString;
    }





}
