package ui.presenter;



import main.ChatManager;
import ui.viewer.ErrorViewer;
import ui.viewer.PseudoPageViewer;

import javax.swing.*;

public class LoginPageController {

    private ChatManager chatManager = new ChatManager();
    private ErrorViewer errorViewer = new ErrorViewer();
    private String passwordTextString;


    private boolean checkLogin(String loginText){
        System.out.println("login: "+loginText);
        System.out.println("login: "+this.chatManager.userLogin());
        if (this.chatManager.userLogin().contentEquals(loginText)){
            return true;
        }
        return false;
    }
    private boolean checkPassword(String passwordText){
        System.out.println("pass: "+passwordText);
        System.out.println("pass: "+this.chatManager.userPassword());
        if (this.chatManager.userPassword().contentEquals(passwordText)){
            return true;
        }
        return false;
    }

    public void onLoginButtonClicked(String loginText, char[] passwordText, JFrame parent) {
        this.passwordTextString = String.valueOf(passwordText);
        System.out.println("login: "+loginText);
        System.out.println("Mot de passe: "+this.passwordTextString);

        if (loginText.isEmpty() || this.passwordTextString.isEmpty()) {
            errorViewer.WarningViewer("Les champs login et password sont obligatoires!!!!!");
        }else if (!this.checkLogin(loginText)) {
            errorViewer.WarningViewer("Le login renseigné est incorrect!!!!");
        }else if (!this.checkPassword(this.passwordTextString)) {
            errorViewer.WarningViewer("Le mot de passe renseigné est incorrect!!!!!");
        }else{
            System.out.println("pseudo fenetre");
            PseudoPageViewer pseudoPage = new PseudoPageViewer(parent);
            parent.dispose();
            pseudoPage.afficherPage();

        }
    }
    public void onCancelButtonClicked(JFrame parent) {
        parent.dispose();
    }
}

