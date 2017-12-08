package ui.viewer;

import main.ChatManager;
import ui.presenter.DialoguePageController;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class DialoguePageViewer extends JFrame{
    private DialoguePageController presenter;
    private JTextArea textArea;
    private JTextField inputTextField;
    private JButton sendButton;
    private JTree listeUsersConnectes;
    private ChatManager chatManager;

    public DialoguePageViewer(String pseudo, ChatManager theCM)throws HeadlessException {
        super();
        this.chatManager = theCM;
        presenter = new DialoguePageController(theCM);
        build(pseudo);


    }
    private void build(String pseudo){
        setTitle("Chat System : Bonjour " + pseudo);
        setSize(600,300);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        Box box = Box.createHorizontalBox();
        add(box, BorderLayout.SOUTH);
        inputTextField = new JTextField();
        inputTextField.addActionListener(e -> presenter.onEnterTouch(inputTextField.getText(), this));
        sendButton = new JButton("Envoyer");
        sendButton.addActionListener(e -> presenter.onSendButton(inputTextField.getText(), this));

        box.add(inputTextField);
        box.add(sendButton);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Utilisateurs Connectés");


        if(!(this.chatManager.accesALaListeDesUsagers().retourneToutLesUsagers().isEmpty())){

            for (String usersCo : this.chatManager.accesALaListeDesUsagers().retourneToutLesUsagers()){
                String pseudoUsersCo  = this.chatManager.accesALaListeDesUsagers().retourneUnUtilisateurDistantParSonLogin(usersCo).getPseudoActuel();
                System.out.println("Pseudo co : "+ pseudoUsersCo);

                if (!(pseudoUsersCo == this.chatManager.userPseudo())) {
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(pseudoUsersCo);
                    root.add(child);
                }
            }
            listeUsersConnectes = new JTree(root);
            listeUsersConnectes.addTreeSelectionListener(e -> presenter.onClickSelection(listeUsersConnectes.getName()));


            add(new JScrollPane(listeUsersConnectes), BorderLayout.WEST);
        }



    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void ajoutTextArea(String text) {
        textArea.append(text);
    }
}