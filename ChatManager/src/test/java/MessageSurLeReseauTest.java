
import connectivite.Entete;
import connectivite.MessageSurLeReseau;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class MessageSurLeReseauTest{

    private static final String FIELD_SEPARATOR = "$";

    private static final Entete ENTETE_TEST = Entete.ENVOIE_MESSAGE;
    private static final String SOME_MESSAGE_A_ENVOYER = "Coucou";

    private ByteArrayOutputStream out;

    @Before
    public void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void Must_Create_A_MessageSurLeReseau_EnvoiMessage(){
        MessageSurLeReseau messageSurLeReseauTest = new MessageSurLeReseau(ENTETE_TEST, SOME_MESSAGE_A_ENVOYER);
        System.out.println(messageSurLeReseauTest);

        String expectedOutput = ENTETE_TEST + FIELD_SEPARATOR + SOME_MESSAGE_A_ENVOYER;

        assertThat(standardOutput(), containsString(expectedOutput));
    }



    private String standardOutput() {
        return out.toString();
    }
}
