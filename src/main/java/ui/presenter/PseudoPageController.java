package ui.presenter;

import connectivite.Entete;
import connectivite.MessageSurLeReseau;
import connectivite.ProtocoleDeCommunication;
import main.ChatManager;
import model.UsersDistants;
import ui.viewer.DialoguePageViewer;
import ui.viewer.ErrorViewer;

import javax.swing.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class PseudoPageController {

    private ErrorViewer errorViewer = new ErrorViewer();
    private ChatManager chatManager = new ChatManager();
    private ProtocoleDeCommunication udpServiceBroadcast = new ProtocoleDeCommunication(chatManager);

    public void onEnterCheckPseudo(String newPseudo, JButton btn) {
        if (newPseudo.isEmpty()) {
            this.errorViewer.WarningViewer("Les champs pseudo est obligatoire!!!!!");
        } else if (!chatManager.verificationCasse(newPseudo)) {
            this.errorViewer.WarningViewer("Attention : les caractères suivants sont interdits: (!,;:?[]()éàè\"')");
        } else if (!chatManager.verificationUnicitePseudo(newPseudo)) {
            this.errorViewer.WarningViewer("Pseudo non Disponible, Vous pouvez utiliser le meme pseudo en ajoutant des nombres");
        } else {
            btn.setEnabled(true);
        }
    }

    public void onChatOuvertureButtonClicked(String pseudo, JFrame parent) {
        chatManager.addPseudoToUser(pseudo);
        MessageSurLeReseau messagePseudoDiffusion = new MessageSurLeReseau(Entete.ENVOIE_USERLOCAL,pseudo);

        if (chatManager.crationCollectionLoginUsersConnectes() != null){
            this.udpServiceBroadcast.envoieDunMessageEnUDP(chatManager.crationCollectionLoginUsersConnectes(), messagePseudoDiffusion);
        }
        DialoguePageViewer dialoguePageViewer = new DialoguePageViewer(pseudo);
        parent.dispose();
        dialoguePageViewer.display();
    }
}
