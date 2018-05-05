package teamgarbo.github.io.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import teamgarbo.github.io.myapplication.Networking.Model.Client;

public class MainActivity extends AppCompatActivity {

    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        client = new Client(this);
    }

    public void testButton(View view)
    {
        //Button button = (Button) findViewById(R.id.test_button);

        client.sendTestMessage();

    }



}
