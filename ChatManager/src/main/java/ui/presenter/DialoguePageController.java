package ui.presenter;

import main.ChatManager;
import model.UsersDistants;
import ui.viewer.DialoguePageViewer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;


public class DialoguePageController {

    private final ChatManager chatManager;

    public DialoguePageController(ChatManager theCM){
        chatManager = theCM;
    }
    public void onSendButton(String text, DialoguePageViewer view) {
        view.ajoutTextArea(text);
        chatManager.getProtocoleDeCommunication().envoieMessage(chatManager.useSessionCourante().getLogin(), text);

    }

    public void onEnterTouch(String text, DialoguePageViewer view) {
        view.ajoutTextArea(text);
    }

    public void onClickSelectionUserCo(String pseudo, DefaultMutableTreeNode root) {
        UsersDistants newUserDistant = chatManager.accesALaListeDesUsagers().retourneUtilisateurDistantsParSonPseudo(pseudo);
        if (pseudo == chatManager.userPseudo()) {
            System.out.println("mise à jour de la liste");
        } else {
            System.out.println("Sélection" + newUserDistant.getPseudoActuel());
            chatManager.useListSessions().addUserDistantToSession(newUserDistant);
            chatManager.getProtocoleDeCommunication().envoieDeDemandeDeSession(newUserDistant.getLogin());
            System.out.println("Après ajout : ");
            chatManager.useListSessions().afficheListeSessions();
            System.out.println("Liste des usagers dans la table :" + chatManager.accesALaListeDesUsagers().retourneToutLesUsagersAsString());
            actualiserMenuSessions(root);
        }
    }

    public void onClickSelectionSession(String pseudoUtilisateurDistantEnChat, DialoguePageViewer view, JButton stopSessionButton){
        chatManager.defenieSessionCourante(pseudoUtilisateurDistantEnChat);

        JLabel titre = new JLabel(pseudoUtilisateurDistantEnChat);
        view.setTitle(chatManager.userPseudo()+" chat avec "+pseudoUtilisateurDistantEnChat);
        //view.add(titre, BorderLayout.NORTH);
        view.modifEtatStopButton(stopSessionButton);

    }

    public void actualiserMenuUsersCo(DefaultMutableTreeNode root) {
        System.out.println("actualiserMenuCo: ext si");
        if (!(this.chatManager.accesALaListeDesUsagers().retourneToutLesUsagers().isEmpty())) {
            System.out.println("actualiserMenuCo: dans si");
            for (String usersCo : this.chatManager.accesALaListeDesUsagers().retourneToutLesUsagers()) {
                if(!usersCo.equals(chatManager.userLogin()) && !usersCo.isEmpty()){
                    String pseudoUsersCo = this.chatManager.accesALaListeDesUsagers().retourneUnUtilisateurDistantParSonLogin(usersCo).getPseudoActuel();
                    System.out.println("Pseudo co : " + pseudoUsersCo);

                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(pseudoUsersCo);
                    root.add(child);
                }
            }
            System.out.println("actualiserMenuCo: fin");
        }
    }

    public void actualiserMenuSessions(DefaultMutableTreeNode root) {
        if (!(this.chatManager.useListSessions().retourneListeSessions().isEmpty())) {

            for (UsersDistants sessionCourante : this.chatManager.useListSessions().retourneListeSessions()) {
                String pseudoUsersSession = sessionCourante.getPseudoActuel();


                if (!(pseudoUsersSession == this.chatManager.userPseudo())) {
                    System.out.println("Session Activée : " + pseudoUsersSession);
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(pseudoUsersSession);
                    root.add(child);
                }
            }
        }
    }

    public void onClickStopButtonSession(JTree listeSessions, String pseudo) {
        UsersDistants userSelectionne = chatManager.accesALaListeDesUsagers().retourneUtilisateurDistantsParSonPseudo(pseudo);
        chatManager.getProtocoleDeCommunication().envoieDeFinDeSession(userSelectionne.getLogin());
        chatManager.useListSessions().removeUserDistantOfSession(pseudo);
        listeSessions.updateUI();

    }
}

