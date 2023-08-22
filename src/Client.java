import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public Client() {

        try {
            Socket socket = new Socket("127.0.0.1", 1221);


        } catch (UnknownHostException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}
