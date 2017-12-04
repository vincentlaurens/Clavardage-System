package model;

import main.ChatManager;

import java.util.HashMap;

public class ListeDesUsagers {

    private ChatManager clavardageManager;

    private static HashMap<String, UsersDistants> listeDesUsersParLeurLogin = new HashMap<String, UsersDistants>();

    public ListeDesUsagers(ChatManager theManager){
        this.clavardageManager = theManager;

    }

    public void ajouteUnUtilisateurDistantALaListe(UsersDistants nouveauUserDistant){
        String leLoginUserDistant = nouveauUserDistant.getLogin();
        listeDesUsersParLeurLogin.put(leLoginUserDistant, nouveauUserDistant);
    }

    public UsersDistants retourneUnUtilisateurDistantParSonLogin(String login){
        UsersDistants leUser = listeDesUsersParLeurLogin.get(login);
        return leUser;
    }



}
