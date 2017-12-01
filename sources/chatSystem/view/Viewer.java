package view;

import javax.swing.*;

public class Viewer extends JFrame{
    private final Controler viewController;

    private JList listeDesUsagersConnectes;


    public Viewer(Controler leController){
        super("Système de clavardage");



        this.viewController = new Controler(this);
    }
}
