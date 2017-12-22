package ui.presenter;

import historique.MessageHistorique;
import main.ChatManager;
import model.ListeDesUsagers;
import model.Sessions;
import model.UsersDistants;
import ui.viewer.DialoguePageViewer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DialoguePageController {

    private final ChatManager chatManager;

    public DialoguePageController(ChatManager theCM){
        chatManager = theCM;
    }
    public void onSendButton(String text, DialoguePageViewer view) {
        view.ajoutTextArea(text);
        chatManager.getProtocoleDeCommunication().envoieMessage(chatManager.useSessionCourante().getLogin(), text );

    }

    public void onEnterTouch(String text, DialoguePageViewer view) {
        view.ajoutTextArea(text);
    }

    public void onClickSelectionUserCo(TreePath selection) {
        String chemin = selection.toString();
        String pseudo = null;
        if(chemin.contains(",")) {
            String[] result = chemin.split(",");
            String[] result2 = result[1].split("]");
            pseudo = result2[0];
        }else{
            String filtre = "^[^\\\\[\\\\]]+\\]";

            Pattern p = Pattern.compile(filtre);

            Matcher matcher = p.matcher(chemin);
            if (!matcher.find()) {
                pseudo = matcher.group();
            }
        }
        System.out.println("onClickSelectionUserCo : "+pseudo);
        if (!(pseudo == null) && !(pseudo.isEmpty()) && !(pseudo.equals("Utilisateurs Connectés")) && !(pseudo.equals(chatManager.userPseudo()))) {
            System.out.println("onClickSelectionUserCo : "+pseudo);
            System.out.println(chatManager.accesALaListeDesUsagers().retourneUtilisateurDistantsParSonPseudo(pseudo));
            UsersDistants newUserDistant = chatManager.accesALaListeDesUsagers().retourneUtilisateurDistantsParSonPseudo(pseudo);
            if(newUserDistant != null) {
                chatManager.defenieSessionCourante(newUserDistant);
                System.out.println("Après ajout : ");
                chatManager.useSessions().afficheListeSessions();

                chatManager.useSessions().addUserDistantToSession(newUserDistant);
                chatManager.getProtocoleDeCommunication().envoieDeDemandeDeSession(newUserDistant.getLogin());
                System.out.println("onClickSectionUserCo fin :" + chatManager.useSessionCourante().toString());
            }
        }
    }

    public void onClickSelectionSession(String pseudoUtilisateurDistantEnChat){
        UsersDistants newUserDistant = chatManager.accesALaListeDesUsagers().retourneUtilisateurDistantsParSonPseudo(pseudoUtilisateurDistantEnChat);
            if(newUserDistant !=null) {
                chatManager.defenieSessionCourante(newUserDistant);
            }

    }

    public DefaultMutableTreeNode actualiserMenuUsersCo() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Utilisateurs Connectés");

        if (!(this.chatManager.accesALaListeDesUsagers().retourneToutLesUsagers().isEmpty())) {
            for (String usersCo : this.chatManager.accesALaListeDesUsagers().retourneToutLesUsagers()) {

                if(!(usersCo == null) && !usersCo.equals(chatManager.userLogin())) {
                    String pseudoUsersCo = this.chatManager.accesALaListeDesUsagers().retourneUnUtilisateurDistantParSonLogin(usersCo).getPseudoActuel();

                    System.out.println("Pseudo co : " + pseudoUsersCo);

                    if(pseudoUsersCo != null) {
                        DefaultMutableTreeNode child = new DefaultMutableTreeNode(pseudoUsersCo);
                        root.add(child);

                    }
                }
            }
        }
        return root;
    }

    public DefaultMutableTreeNode actualiserMenuSessions(){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Sessions Actives");
        if(!(chatManager.useSessions().retourneListeSessions().isEmpty())){
            for(Map.Entry<UsersDistants, MessageHistorique> entry : chatManager.useSessions().retourneListeSessions().entrySet()) {
                String pseudoUsersSession = entry.getKey().getPseudoActuel();
                System.out.println("Session Activée : " + pseudoUsersSession);
                if (!(pseudoUsersSession.equals(chatManager.userPseudo()))) {
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(pseudoUsersSession);
                    root.add(child);
                }
            }
        }
        return root;
    }

    public void onClickStopButtonSession(JTree listeSessions, String pseudo) {
        UsersDistants userSelectionne = chatManager.accesALaListeDesUsagers().retourneUtilisateurDistantsParSonPseudo(pseudo);
        chatManager.getProtocoleDeCommunication().envoieDeFinDeSession(userSelectionne.getLogin());
        chatManager.useSessions().removeUserDistantOfSession(userSelectionne);
        listeSessions.updateUI();

    }

}
