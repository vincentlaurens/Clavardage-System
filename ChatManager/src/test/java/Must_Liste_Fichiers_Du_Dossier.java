import historique.*;
import main.ChatManager;
import model.Sessions;
import model.UsersDistants;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




public class Must_Liste_Fichiers_Du_Dossier {

    private Sessions session;
    private MessageHistorique monMessageHistorique;





    @Test
    public void Affichage_Liste_Fichiers_Dossier() {
        ChatManager theCM = new ChatManager();
        UsersDistants user = new UsersDistants("toto", "localhost", "titi", 1045);
        String message = "Salut ça va !!!!!";
        monMessageHistorique = new MessageHistorique(theCM);
        theCM.useSessions().definieUserDistantCourant(user);
        try {
            monMessageHistorique.creerFichier();
            monMessageHistorique.listeFichiersDossier();
            monMessageHistorique.affichageListeFichierDuDossier();
            File fichierTrouver = monMessageHistorique.findfichier();
            System.out.println(fichierTrouver);
            System.out.println(fichierTrouver.getName());
            if (!fichierTrouver.getName().isEmpty()) {
                System.out.println("Fichier trouvé : " + fichierTrouver.toString());
                monMessageHistorique.ecriturefichier(MomentEcriture.MESSAGE_RECU,message);
            } else {
                System.out.println("Fichier non trouvé");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFileException e) {
            e.printStackTrace();
        } catch (NotDirectoryException e) {
            e.printStackTrace();
        } catch (CasseNomFichierException e) {
            e.printStackTrace();
        }


    }
}