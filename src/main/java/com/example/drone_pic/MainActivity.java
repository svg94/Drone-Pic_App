package com.example.drone_pic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    Button start;
    Button land;
    Button pic;
    Button up;
    Button down;
    Button fwd;
    Button back;

    ClientSender clientSender;
    UDP_Client_Receiver clientReceiver;
    Drone tello;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clientSender = new UDP_ClientSender(8889,"192.168.10.1");
        clientReceiver = new UDP_Client_Receiver(8890,"0.0.0.0");
        clientReceiver.setDaemon(true);
        clientReceiver.start();

        tello = new TelloDrone(clientSender,clientReceiver);

        //client = new UDP_Client(8889,"192.168.10.1");
        //tello = new TelloDrone(client);
        start = (Button) findViewById(R.id.b_start);
        land = (Button) findViewById(R.id.b_land);
        pic = (Button) findViewById(R.id.b_pic);
        up = (Button) findViewById(R.id.b_up);
        down = (Button) findViewById(R.id.b_down);
        fwd = (Button) findViewById(R.id.b_vor);
        back = (Button) findViewById(R.id.b_back);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"KOMMUNIKATION IST A UND O.",Toast.LENGTH_SHORT).show();
                tello.connect();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Sende Drohne (hoffentlich) ins Jenseits.",Toast.LENGTH_SHORT).show();
                tello.start();
            }
        });

        land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Landung wird (hoffentlich) eingeleitet.",Toast.LENGTH_SHORT).show();
                tello.land();
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tello.up(20);
                Toast.makeText(getApplicationContext(),clientReceiver.getStates(),Toast.LENGTH_SHORT).show();
                System.out.println("/////////////////////////");
                System.out.println(clientReceiver.getStates());
                System.out.println("/////////////////////////");
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.down(20);
            }
        });
        fwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.forward(20);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.back(20);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}