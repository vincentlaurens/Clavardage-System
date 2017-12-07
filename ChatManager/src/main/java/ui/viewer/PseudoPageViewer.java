package ui.viewer;

import connectivite.AdresseIpSysteme;
import main.ChatManager;
import ui.presenter.PseudoPageController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PseudoPageViewer extends JDialog {

    private final ChatManager chatManager;
    private final JLabel labelPseudo;
    private final JTextField textFieldPseudo;
    private final JButton  btnConnexion;
    private final PseudoPageController pseudoPageController;
    private final JLabel labelinterfaceReseau;
    private final JTextField textFieldIp;
    private final AdresseIpSysteme ipSysteme;
    private final ErrorViewer erreurPage;
    private final JTextField textFieldCasse;

    public PseudoPageViewer(JFrame parent, ChatManager theChatManager)  {
        super (parent, "Choix du Pseudo", true);

        pseudoPageController = new PseudoPageController(theChatManager);
        ipSysteme = new AdresseIpSysteme();
        erreurPage = new ErrorViewer();

        chatManager = theChatManager;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;



        textFieldCasse = new JTextField(40);
        textFieldCasse.setText("Attention : Caractères interdits dans le Pseudo : ()'\",;:!§ùàç&éè-_@[]}{/#~=*+<>");
        textFieldCasse.setEditable(false);
        JPanel regles = new JPanel();
        regles.add(textFieldCasse);
        regles.setVisible(true);

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

        labelinterfaceReseau = new JLabel("Votre Adresse Ip sur le réseau est: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(labelinterfaceReseau, cs);

        textFieldIp = new JTextField(10);
        textFieldIp.setEditable(false);
        try {
            textFieldIp.setText(ipSysteme.ipInterfaceReseauToUser(chatManager));
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(textFieldIp, cs);

        btnConnexion = new JButton("Lancer le Chat");
        btnConnexion.setEnabled(false);
        btnConnexion.addActionListener(e-> pseudoPageController.onChatOuvertureButtonClicked(textFieldPseudo.getText(), parent));

        textFieldPseudo.addActionListener(e-> pseudoPageController.onEnterCheckPseudo(textFieldPseudo.getText(), btnConnexion));

        JPanel bp = new JPanel();
        bp.add(btnConnexion);
        bp.setVisible(true);

        getContentPane().add(regles, BorderLayout.BEFORE_FIRST_LINE);
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