package ui.presenter;



import main.ChatManager;
import model.Sessions;
import ui.viewer.ErrorViewer;
import ui.viewer.PseudoPageViewer;

import javax.swing.*;
import java.io.IOException;

public class LoginPageController {

    private ChatManager chatManager;
    private ErrorViewer errorViewer = new ErrorViewer();
    private String passwordTextString;

    public LoginPageController(ChatManager theCM){
        this.chatManager = theCM;
    }


    private boolean checkLogin(String loginText){
        if (this.chatManager.userLogin().contentEquals(loginText)){
            return true;
        }
        return false;
    }
    private boolean checkPassword(String passwordText){
        if (this.chatManager.userPassword().contentEquals(passwordText)){
            return true;
        }
        return false;
    }

    public void onLoginButtonClicked(String loginText, char[] passwordText, JFrame parent) throws IOException {
        this.passwordTextString = String.valueOf(passwordText);

        if (loginText.isEmpty() || this.passwordTextString.isEmpty()) {
            errorViewer.WarningViewer("Les champs login et password sont obligatoires!!!!!");
        }else if (!this.checkLogin(loginText)) {
            errorViewer.WarningViewer("Le login renseigné est incorrect!!!!");
        }else if (!this.checkPassword(this.passwordTextString)) {
            errorViewer.WarningViewer("Le mot de passe renseigné est incorrect!!!!!");
        }else{
            PseudoPageViewer pseudoPage = new PseudoPageViewer(parent, this.chatManager);
            chatManager.getProtocoleDeCommunication().demandeDeConnexion();
            parent.dispose();
            pseudoPage.afficherPage();

        }
    }
    public void onCancelButtonClicked(JFrame parent) {
        parent.dispose();
    }
}

