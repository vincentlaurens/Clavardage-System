import connectivite.Entete;
import connectivite.MessageSurLeReseau;
import connectivite.TCP_EnvoieMessage;
import connectivite.UDP_EnvoieMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TestEnvoieMessage {

    private static final String SOME_IP = "127.0.0.1" ;
    private static final int SOME_PORT = 1234;

    private static final MessageSurLeReseau SOME_MESSAGE = new MessageSurLeReseau(Entete.ENVOIE_MESSAGE, "MESSAGE");
    private TCP_EnvoieMessage tcpMessageSender;
    private UDP_EnvoieMessage udpMessageSender;



    @Before
    public void setUp() throws Exception {
        initMocks(this);
   }


    @Test
    public void should_send_message_in_TCP_with_information_given_by_user() throws Exception {

        tcpMessageSender = new TCP_EnvoieMessage();

        tcpMessageSender.sendMessageOn(SOME_IP,SOME_PORT,SOME_MESSAGE);

        verify(tcpMessageSender).sendMessageOn(SOME_IP, SOME_PORT, SOME_MESSAGE);
    }

    @Test
    public void should_send_message_in_UDP_with_information_given_by_user() throws Exception {

        udpMessageSender = new UDP_EnvoieMessage();

        udpMessageSender.sendMessageOn(SOME_PORT,SOME_MESSAGE);

        verify(udpMessageSender).sendMessageOn( SOME_PORT, SOME_MESSAGE);
    }
}
