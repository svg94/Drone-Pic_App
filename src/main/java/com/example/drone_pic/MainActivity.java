package com.example.drone_pic;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.drone_pic.Client.ClientSender;
import com.example.drone_pic.Client.UDP_ClientSender;
import com.example.drone_pic.Client.UDP_Client_Receiver;
import com.example.drone_pic.Drone.Drone;
import com.example.drone_pic.Drone.TelloDrone;

import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {
    LinkedList<Button> buttons = new LinkedList<>();
    ClientSender clientSender;
    UDP_Client_Receiver clientReceiver;
    Drone tello;
    final int DRONESPEED = 30;


    @SuppressLint("ClickableViewAccessibility")
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
        int[] button_ids = {R.id.b_start, R.id.b_land, R.id.b_pic,
                            R.id.b_up, R.id.b_down, R.id.b_vor,
                            R.id.b_back, R.id.b_left, R.id.b_right,
                            R.id.b_tLeft, R.id.b_tRight};

        for(int i = 0; i< button_ids.length;i++){
            buttons.add((Button) findViewById(button_ids[i]));
        }


        buttons.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"KOMMUNIKATION IST A UND O.",Toast.LENGTH_SHORT).show();
                tello.connect();
            }
        });

        buttons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Sende Drohne (hoffentlich) ins Jenseits.",Toast.LENGTH_SHORT).show();
                tello.start();
            }
        });

        buttons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Landung wird (hoffentlich) eingeleitet.",Toast.LENGTH_SHORT).show();
                tello.land();
            }
        });
        buttons.get(3).setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.up(DRONESPEED);
            }
        }));
        buttons.get(4).setOnTouchListener(new RepeatListener(400,100,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.down(DRONESPEED);
            }
        }));
        buttons.get(5).setOnTouchListener(new RepeatListener(400,100,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.forward(DRONESPEED);
            }
        }));
        buttons.get(6).setOnTouchListener(new RepeatListener(400,100,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.back(DRONESPEED);
            }
        }));
        buttons.get(7).setOnTouchListener(new RepeatListener(400,100,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.left(DRONESPEED);
            }
        }));
        buttons.get(8).setOnTouchListener(new RepeatListener(400,100,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.right(DRONESPEED);
            }
        }));
        buttons.get(9).setOnTouchListener(new RepeatListener(400,100,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.turnLeft(DRONESPEED);
            }
        }));
        buttons.get(10).setOnTouchListener(new RepeatListener(400,100,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tello.turnRight(DRONESPEED);
            }
        }));
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