import connectivite.AdresseIpSysteme;
import main.ChatManager;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class IPShowTest {

    private ByteArrayOutputStream out;
    @Before
    public void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void Must_show_all_ip_interfaces_of_computer() throws IOException {

        AdresseIpSysteme ip = new AdresseIpSysteme();
        try {
            String ipreturn = ip.IpInterfaceReseauToUser();
            String expectedOutput = "Adresse IP locale = 10.203.2.15";
            System.out.println("Adresse IP locale = "+ ipreturn);
            assertThat(standardOutput(), containsString(expectedOutput));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private String standardOutput() {
        return out.toString();
    }
}

