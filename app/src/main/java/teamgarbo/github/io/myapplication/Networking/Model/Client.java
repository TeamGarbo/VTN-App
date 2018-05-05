package teamgarbo.github.io.myapplication.Networking.Model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import teamgarbo.github.io.myapplication.Networking.Messages.Message;
import teamgarbo.github.io.myapplication.Networking.Messages.StringMessage;

/**
 * Created by hercu on 05-May-18.
 */

public class Client {

    String TAG = "-CLIENT";


    String IP = "152.78.1.81";

    Context context;

    String playerID; //This can be anything that identifies the user
    String playerName;

    Socket socket;
    ObjectOutputStream os;
    ObjectInputStream is;

    boolean socketInitalised = false;


    public Client(Context context)
    {
        this.context = context;

        initPlayerID();

        startSocket();
    }

    private void initSocket() throws IOException {
        //String IP = "10.9.133.81";

        InetAddress adr = InetAddress.getByName(IP);

        socket = new Socket(adr, 4444);
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());
        socketInitalised = true;
    }


    private void initPlayerID() {

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        // get IMEI Permission
        if (ActivityCompat.checkSelfPermission(context,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
            //initPlayerID();
            //return;
        }

        playerID = tm.getDeviceId();
    }

    public void sendTestMessage()
    {
        try {
            sendMessage(new StringMessage(playerID, "This is a test!"));
            Log.e(TAG,"sending test message");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(final Message message) throws IOException {
        new Thread()
        {
            public void run() {
                try {
                    if(!socketInitalised) initSocket();
                    System.out.println(message.getPlayerID());
                    os.writeObject(message);
                    os.flush(); // Send off the data
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void startSocket(){
        new Thread()
        {
            public void run() {
                try {
                    if(!socketInitalised) initSocket();
                    //listener();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void listener(){
        new Thread()
        {
            public void run() {
                try {
                    while(socketInitalised){
                        Message message = (Message) is.readObject();
                        processMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //TODO: process message based on type
    private void processMessage(Message message){

        if(message instanceof StringMessage)
        {

        }

    }
}
