package com.example.drone_pic.Drone;
import android.os.StrictMode;

import com.example.drone_pic.Client.ClientReceiver;
import com.example.drone_pic.Client.ClientSender;

import java.net.DatagramPacket;
public class TelloDrone implements Drone {
    long timeLastCommand;
    double time_btw_cmd = 1.0;      //Für lange cmds wie Start/Land
    double rc_time_btw_cmd = 0.5;
    ClientSender clientSender;
    ClientReceiver clientReceiver;

    public TelloDrone(ClientSender c, ClientReceiver r){
        clientSender = c;
        clientReceiver = r;
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

    //Short CMDs RC_CONTROL
    @Override
    public void up(int x) {
        this.command("up "+x, time_btw_cmd);
    }
    @Override
    public void down(int x) {
        this.command("down "+x, rc_time_btw_cmd);
    }
    @Override
    public void forward(int x) {
        this.command("forward "+x, rc_time_btw_cmd);
    }
    @Override
    public void back(int x) { this.command("back "+x, rc_time_btw_cmd); }
    @Override
    public void left(int x) {
        this.command("left "+x, rc_time_btw_cmd);
    }
    @Override
    public void right(int x) { this.command("right "+x,rc_time_btw_cmd); }
    @Override
    public void turnRight(int x) {
        this.command("cw "+x, rc_time_btw_cmd);
    }
    @Override
    public void turnLeft(int x) {
        this.command("ccw "+x, rc_time_btw_cmd);
    }
    @Override
    public void backFlip() { this.command("flip b",rc_time_btw_cmd); }

    //GET_STATES
    @Override
    public boolean isConnected() {
        return clientSender.isConnected();
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
                    clientSender.connect();
                }
                if(null == cmd || 0 == cmd.length())
                    return; //"empty command";
                if(!clientSender.isConnected()) {
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
                final DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientSender.getServer(), clientSender.getPort());
                clientSender.send(sendPacket);
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
    public int getPort() {
        return 0;
    }



    @Override
    public String getIP() {
        return null;
    }
}
