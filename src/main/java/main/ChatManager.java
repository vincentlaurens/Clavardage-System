package main;

import connectivite.ProtocoleDeCommunication;
import model.ListeDesUsagers;
import model.Sessions;
import model.UserLocal;
import model.UsersDistants;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Set;
import java.util.Iterator;

public class ChatManager {
    private ProtocoleDeCommunication protocoleDeCommunication = new ProtocoleDeCommunication(this);
    private final ListeDesUsagers listeDesUsagers = new ListeDesUsagers(this);
    private final Sessions session;
    private UserLocal user;


    public ChatManager(){
        this.session = new Sessions();
        this.user = new UserLocal(null, null, "vince", "toto");
    }

    public ListeDesUsagers accesALaListeDesUsagers(){
        return listeDesUsagers;
    }

    public String userLogin(){
        return  this.user.useLoginUser();
    }
    public String userPassword(){
        return this.user.usePasswordUser();
    }
    public void addPseudoToUser(String pseudo){
        this.user.userPseudoadd(pseudo);
    }
    public void addIpadressToUser(String IpAdress){
        this.user.userIPAdressadd(IpAdress);
    }
    public String userIp(){return this.user.useIpUser();}

    public boolean verificationUnicitePseudo(String pseudo) {
        boolean pseudoUnique = true;
        if (!this.listeDesUsagers.retourneListeUtilistaeursDistants().isEmpty()) {
            Set usersCo = this.listeDesUsagers.retourneListeUtilistaeursDistants().entrySet();
            Iterator it = usersCo.iterator();
            while (it.hasNext() || pseudoUnique) {
                Object hashmapposition = it.next();
                UsersDistants userDistantCourant = this.listeDesUsagers.retourneListeUtilistaeursDistants().get(hashmapposition);
                if (userDistantCourant.getPseudo().equalsIgnoreCase(pseudo)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verificationCasse(String newPseudo) {
        if (newPseudo != null) {
            String regex = "^[^,;/\\\\.@é\"\"'{}()_+\\-èàç\\^ïëêî!\\?\\.µ$£§¤#~&°]+$"; /*" /^[^@&\"()!_$*€£`+=;?#éèçà,:ù%µ§]+$/";*/
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(newPseudo);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public String[] crationCollectionLoginUsersConnectes(){
        if (!this.accesALaListeDesUsagers().retourneListeUtilistaeursDistants().isEmpty()) {
            String[] loginUsersCo = new String[this.accesALaListeDesUsagers().retourneListeUtilistaeursDistants().size()];
            Set usersCo = this.accesALaListeDesUsagers().retourneListeUtilistaeursDistants().entrySet();
            Iterator it = usersCo.iterator();
            int i = 0;
            while (it.hasNext()) {
                Object hashmapposition = it.next();
                UsersDistants userDistantCourant = this.accesALaListeDesUsagers().retourneListeUtilistaeursDistants().get(hashmapposition);
                loginUsersCo[i] = userDistantCourant.getLogin();
                i++;
            }
            return loginUsersCo;
        }

        return null;
    }

    public void crationCollectionPseudoUsersConnectes(){
        if (!this.accesALaListeDesUsagers().retourneListeUtilistaeursDistants().isEmpty()) {
            String[] PseudoUsersCo = new String[this.accesALaListeDesUsagers().retourneListeUtilistaeursDistants().size()];
            Set usersCo = this.accesALaListeDesUsagers().retourneListeUtilistaeursDistants().entrySet();
            Iterator it = usersCo.iterator();
            int i = 0;
            while (it.hasNext()) {
                Object hashmapposition = it.next();
                UsersDistants userDistantCourant = this.accesALaListeDesUsagers().retourneListeUtilistaeursDistants().get(hashmapposition);
                PseudoUsersCo[i] = userDistantCourant.getPseudo();
                i++;
            }

        }

    }
}







