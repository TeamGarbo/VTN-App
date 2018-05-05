package teamgarbo.github.io.myapplication.Networking.Messages;

/**
 * Created by hercu on 05-May-18.
 */

public class StringMessage extends Message {

    String string;

    public StringMessage(String playerID, String message) {
        super(playerID);
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
