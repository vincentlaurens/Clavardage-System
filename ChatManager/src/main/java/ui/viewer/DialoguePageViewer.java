package ui.viewer;

import main.ChatManager;
import ui.presenter.DialoguePageController;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class DialoguePageViewer extends JFrame{
    private DialoguePageController presenter = new DialoguePageController();
    private JTextArea textArea;
    private JTextField inputTextField;
    private JButton sendButton;
    private JTree listeUsersConnectes;
    private ChatManager chatManager;

    public DialoguePageViewer(String pseudo, ChatManager theCM)throws HeadlessException {
        super();
        this.chatManager = theCM;
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
        sendButton = new JButton("Envoyer");
        box.add(inputTextField);
        box.add(sendButton);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Utilisateurs Connectés");


        if(!(this.chatManager.accesALaListeDesUsagers().retourneToutLesUsagers().isEmpty())){
            int tailleDeLaListeDesUsersCos = chatManager.accesALaListeDesUsagers().retourneToutLesUsagers().size();

            String[] usersCo= new String[tailleDeLaListeDesUsersCos];
            for (String pseudoUsersCo : usersCo){
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(pseudoUsersCo);
                root.add(child);
            }
            listeUsersConnectes = new JTree(root);
            add(new JScrollPane(listeUsersConnectes), BorderLayout.WEST);
        }



    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}