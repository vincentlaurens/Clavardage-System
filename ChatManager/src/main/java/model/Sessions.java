package model;

import historique.MessageHistorique;
import main.ChatManager;


import java.util.HashMap;

public class Sessions {

    private final ChatManager chatManager;
    private UsersDistants sessionEnCoursDeChat;
    private HashMap<UsersDistants, MessageHistorique> listeSessions;

    public Sessions(ChatManager theChatManager){
        chatManager = theChatManager;
        listeSessions = new HashMap<>();
    }

    public void addUserDistantToSession(UsersDistants usersDistants) {
        MessageHistorique messageHistorique = new MessageHistorique(chatManager);
        if (!listeSessions.containsKey(usersDistants)){
            listeSessions.put(usersDistants, messageHistorique);
        }
    }

    public void afficheListeSessions() {
        if(!listeSessions.isEmpty()) {
            listeSessions.toString();
        }
    }

    public HashMap<UsersDistants, MessageHistorique> retourneListeSessions(){
        return listeSessions;
    }

    public void removeUserDistantOfSession(UsersDistants userARemove) {
        listeSessions.remove(userARemove);
    }

    public void definieUserDistantCourant(UsersDistants utilisateurDistantEnChat) {
        System.out.println("definieUserDistantCourant "+utilisateurDistantEnChat.toString());
        sessionEnCoursDeChat = utilisateurDistantEnChat;
    }

    public UsersDistants userDistantSessionCourante() {
        return sessionEnCoursDeChat;
    }
}