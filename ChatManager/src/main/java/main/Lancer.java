package main;

import ui.viewer.LoginPageViewer;

import javax.swing.*;

public class Lancer {

    public static void main(String[] args){


        JFrame frame = new JFrame();

        LoginPageViewer loginDialog = new LoginPageViewer(frame);
        loginDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginDialog.setVisible(true);

    }
}
