package teamgarbo.github.io.myapplication.Networking.Messages;

import java.io.Serializable;

/**
 * Created by hercu on 05-May-18.
 */

public class Message implements Serializable{

    private String playerID;

    public Message(String playerID){
        this.playerID = playerID;
    }

    public String getPlayerID() {
        return playerID;
    }

}
