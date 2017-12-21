package ui.presenter;

import main.ChatManager;
import model.Sessions;
import model.UsersDistants;
import ui.viewer.DialoguePageViewer;


public class DialoguePageController {

    private final ChatManager chatManager;

    public DialoguePageController(ChatManager theCM){
        chatManager = theCM;
    }
    public void onSendButton(String text, DialoguePageViewer view) {
        view.ajoutTextArea(text);
        // chatManager.getProtocoleDeCommunication().();

    }

    public void onEnterTouch(String text, DialoguePageViewer view) {
        view.ajoutTextArea(text);
    }

    public void onClickSelection(String pseudo) {
        UsersDistants newUserDistant = chatManager.accesALaListeDesUsagers().retourneUtilisateurDistantsParSonPseudo(pseudo);
        System.out.println("Sélection" +newUserDistant.getPseudoActuel());
        chatManager.useListSessions().addUserDistantToSession(newUserDistant);
        System.out.println("Après ajout : ");
        chatManager.useListSessions().afficheListeSessions();
        System.out.println("Liste des usagers dans la table :" +chatManager.accesALaListeDesUsagers().retourneToutLesUsagersAsString());
    }
}

