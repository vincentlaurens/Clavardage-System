package ui.viewer;

import ui.presenter.LoginPageController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class LoginPageViewer extends JDialog{
    private final JLabel labelLogin, labelMotDePasse;
    private final JTextField textFieldLogin;
    private final JPasswordField fieldMotdePasse;
    private final JButton btnLogin, btnCancel;
    private LoginPageController loginController;

    public LoginPageViewer(JFrame parent) {

        super(parent, "Connexion", true);

        this.loginController = new LoginPageController();
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        labelLogin = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(labelLogin, cs);

        textFieldLogin = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(textFieldLogin, cs);

        labelMotDePasse = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(labelMotDePasse, cs);

        fieldMotdePasse = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(fieldMotdePasse, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> {
            try {
                this.loginController.onLoginButtonClicked(textFieldLogin.getText(), fieldMotdePasse.getPassword(), parent);
            } catch ( IOException e1 ) {
                e1.printStackTrace();
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e-> this.loginController.onCancelButtonClicked(parent));
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);



    }
}
