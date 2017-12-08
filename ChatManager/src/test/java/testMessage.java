import connectivite.Entete;
import connectivite.MessageSurLeReseau;
import main.ChatManager;
import model.ListeDesUsagers;
import model.UserLocal;
import model.UsersDistants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.UnknownServiceException;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class testMessage{

    private static final String SOME_LOGIN = "THEPSEUDO";

    private static final String SOME_IP_ADDRESS = "127.0.0.1";
    private static final String SOME_PSEUDO = "Coucou";
    private static final String SOME_PASSWORD = "1234";

    private ByteArrayOutputStream out;
    private ChatManager testManager = new ChatManager();
    private ListeDesUsagers listeDesUsagers = new ListeDesUsagers(testManager);



    @Test
    public void Must_Create_A_List_Of_User(){
        listeDesUsagers.retourneToutLesUsagersAsString();

    }

    @Test
    public void MUST_ADD_USAGER_DISTANT_A_LA_LISTE(){
        UserLocal userLocal = new UserLocal(SOME_PSEUDO, SOME_IP_ADDRESS, SOME_LOGIN, SOME_PASSWORD);
        UsersDistants dist = userLocal.retourneUserLocalAsDistant();
        testManager.accesALaListeDesUsagers().ajouteUnUtilisateurDistantALaListe(dist);
        System.out.println(testManager.accesALaListeDesUsagers().retourneToutLesUsagersAsString());

    }

    @Test
    public void MUST_RECUPERER_LES_USERS_DISTANT(){
        UserLocal userLocal = new UserLocal(SOME_PSEUDO, SOME_IP_ADDRESS, SOME_LOGIN, SOME_PASSWORD);
        UsersDistants dist = userLocal.retourneUserLocalAsDistant();
        testManager.accesALaListeDesUsagers().ajouteUnUtilisateurDistantALaListe(dist);
        String chaineTest = testManager.accesALaListeDesUsagers().retourneToutLesUsagersAsString();
        System.out.println(testManager.accesALaListeDesUsagers().retourneToutLesUsagersAsString());
        String[] users = chaineTest.split("[/]");
        for (String user :users) {

            String elements[] = user.split("[,]");
            int i = 0;
            for (String element: elements) {

                System.out.println("Element " + i+ " "+element.toString());
                i++;
            }


        }


        testManager.accesALaListeDesUsagers().ajouteToutLesUsagers(chaineTest);
        System.out.println(testManager.accesALaListeDesUsagers().retourneToutLesUsagersAsString());



    }




}
