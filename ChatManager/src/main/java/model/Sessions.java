package model;

import java.util.ArrayList;

public class Sessions {

    private ArrayList<UsersDistants> listeSessions;

    public Sessions(){
        listeSessions = new ArrayList<>();

    }

    public void addUserDistantToSession(UsersDistants usersDistants){
        listeSessions.add(usersDistants);

    }

    public void afficheListeSessions(){
        for (UsersDistants courant: listeSessions) {
            System.out.println(courant.getPseudoActuel());

        }
    }
}
