package com.example.drone_pic;
import android.os.StrictMode;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
public class TelloDrone implements Drone {
    long timeLastCommand;
    double time_btw_cmd = 1.0;      //Für lange cmds wie Start/Land
    double rc_time_btw_cmd = 0.5;
    Client client;
    public TelloDrone(Client c){
        client = c;
    }

    //LONG CMDs
    public void start(){
        this.command("takeoff", time_btw_cmd);
    }
    public void land(){
        this.command("land", time_btw_cmd);
    }
    public void connect(){
        this.command("command", time_btw_cmd);
    }

    //Short CMDs
    @Override
    public void up(int x) {
        this.command("up 20", rc_time_btw_cmd);
    }

    @Override
    public void down(int x) {
        this.command("down 20", rc_time_btw_cmd);
    }

    @Override
    public boolean isConnected() {
        return client.isConnected();
    }

    @Override
    public void command(String cmd, double timeBtw){        //LONG COMMAND SENDING TIME
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
            try {
                if(!isConnected()){
                    client.connect();
                }
                if(null == cmd || 0 == cmd.length())
                    return; //"empty command";
                if(!client.isConnected()) {
                    return; //"disconnected";
                }
                long differenz;

                differenz = System.currentTimeMillis() * 1000 - timeLastCommand;    //SPAM-SCHUTZ
                if (differenz < timeBtw) {
                    try {
                        Thread.sleep(differenz);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }                                                                   //SPAM-SCHUTZ ENDE
                final byte[] sendData = cmd.getBytes();
                final DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.getServer(), client.getPort());
                client.send(sendPacket);
                System.out.println("Sent command: " + cmd);
                timeLastCommand = System.currentTimeMillis()*1000;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    @Override
    public int getBattery() {
        return 0;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed(int speed) {

    }

    @Override
    public int getTime() {
        return 0;
    }

    @Override
    public boolean backFlip() {
        return false;
    }


    @Override
    public boolean left(int x) {
        return false;
    }

    @Override
    public boolean right(int x) {
        return false;
    }

    @Override
    public boolean forward(int x) {
        return false;
    }

    @Override
    public boolean back(int x) {
        return false;
    }

    @Override
    public boolean turnRight(int x) {
        return false;
    }

    @Override
    public boolean turnLeft(int x) {
        return false;
    }

    @Override
    public int getPort() {
        return 0;
    }



    @Override
    public String getIP() {
        return null;
    }
}
