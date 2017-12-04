package ui.viewer;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class ErrorViewer extends JFrame{
    public void afficherErreur(String message) {
        showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    public void WarningViewer(String message){
        showMessageDialog(this, message, "Erreur", JOptionPane.WARNING_MESSAGE);
    }
}
