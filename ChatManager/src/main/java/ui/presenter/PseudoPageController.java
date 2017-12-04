package ui.presenter;

import main.ChatManager;
import ui.viewer.DialoguePageViewer;
import ui.viewer.ErrorViewer;

import javax.swing.*;

public class PseudoPageController {

    private ErrorViewer errorViewer  = new ErrorViewer();
    private ChatManager chatManager = new ChatManager();

    public void onEnterCheckPseudo(String newPseudo, JButton btn){
        if (newPseudo.isEmpty()){
            this.errorViewer.WarningViewer("Les champs pseudo est obligatoire!!!!!");
        }else {
            this.errorViewer.WarningViewer("Login Disponible!!!");
            btn.setEnabled(true);
        }

    }

    public void onChatOuvertureButtonClicked(String pseudo, JFrame parent) {
        chatManager.addPseudoToUser(pseudo);
        DialoguePageViewer dialoguePageViewer = new DialoguePageViewer(pseudo);
        parent.dispose();
        dialoguePageViewer.display();

        //this.errorViewer.WarningViewer("Ouverture Page de chat!!!");
    }
}
