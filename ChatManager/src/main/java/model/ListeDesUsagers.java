package model;

import main.ClavardageManager;

import java.util.HashMap;

public class ListeDesUsagers {

    private ClavardageManager clavardageManager;

    private static HashMap<String, UsersDistants> listeDesUsersParLeurLogin = new HashMap<String, UsersDistants>();

    public ListeDesUsagers(ClavardageManager theManager){
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
