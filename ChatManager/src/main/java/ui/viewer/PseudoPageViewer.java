package ui.viewer;

import ui.presenter.PseudoPageController;

import javax.swing.*;
import java.awt.*;

public class PseudoPageViewer extends JDialog {


    private final JLabel labelPseudo;
    private final JTextField textFieldPseudo;
    private final JButton  btnConnexion;
    private final PseudoPageController pseudoPageController;

    public PseudoPageViewer(JFrame parent){
        super (parent, "Choix du Pseudo", true);

        this.pseudoPageController = new PseudoPageController();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        labelPseudo = new JLabel("Nouveau Pseudo: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(labelPseudo, cs);

        textFieldPseudo = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(textFieldPseudo, cs);



        btnConnexion = new JButton("Lancer le Chat");
        btnConnexion.setEnabled(false);
        btnConnexion.addActionListener(e-> this.pseudoPageController.onChatOuvertureButtonClicked(this.textFieldPseudo.getText(), parent));

        textFieldPseudo.addActionListener(e-> this.pseudoPageController.onEnterCheckPseudo(this.textFieldPseudo.getText(), btnConnexion));

        JPanel bp = new JPanel();
        bp.add(btnConnexion);
        bp.setVisible(true);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public void afficherPage() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
