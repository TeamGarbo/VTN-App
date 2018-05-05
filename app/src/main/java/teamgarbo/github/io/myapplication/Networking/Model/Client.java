package teamgarbo.github.io.myapplication.Networking.Model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by hercu on 05-May-18.
 */

public class Client {

    String playerID; //This can be anything that identifies the user
    String playerName;

    Socket socket;
    ObjectOutputStream os;
    ObjectInputStream is;
    boolean socketInitalised = false;
}
