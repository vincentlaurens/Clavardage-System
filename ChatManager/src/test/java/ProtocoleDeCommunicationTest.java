import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import connectivite.Entete;
import connectivite.ProtocoleDeCommunication;
import main.ChatManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ProtocoleDeCommunicationTest {

    private final Entete ENVOIE_MESSAGE_ENTETE = Entete.ENVOIE_MESSAGE;
    private final Entete ENVOIE_USER_LOCAL_ENTETE = Entete.ENVOIE_USERLOCAL;

    private final String FIELDSEPARATOR = "$";

    private final String SOME_CONTENT_MESSAGE = "Ceci est un message";
    private final String SOME_CONTENT_USERLOCAL ="LeLogin,LeIP,LePseudo,lePort";

    private ByteArrayOutputStream out;

    @Before
    public void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void should_print_incoming_messages() throws Exception {
        String receivedMessage = ENVOIE_MESSAGE_ENTETE + FIELDSEPARATOR + SOME_CONTENT_MESSAGE;
        ChatManager clavardageManager = new ChatManager();

        ProtocoleDeCommunication protocoleDeCommunication = new ProtocoleDeCommunication(clavardageManager);
        protocoleDeCommunication.onNewIncomingMessage(receivedMessage);

        String expectedOutput = SOME_CONTENT_MESSAGE;

        assertThat(standardOutput(), containsString(expectedOutput));
    }

    @Test
    public void should_print_userLocalDistant() throws Exception {
        String receivedMessage = ENVOIE_MESSAGE_ENTETE + FIELDSEPARATOR + SOME_CONTENT_MESSAGE;
        ChatManager clavardageManager = new ChatManager();

        ProtocoleDeCommunication protocoleDeCommunication = new ProtocoleDeCommunication(clavardageManager);
        protocoleDeCommunication.onNewIncomingMessage(receivedMessage);

        String expectedOutput = SOME_CONTENT_MESSAGE;

        assertThat(standardOutput(), containsString(expectedOutput));
    }

    private String standardOutput() {
        return out.toString();
    }
}
