package com.example.drone_pic;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
public class TelloDrone implements Drone {
    Client client;
    public TelloDrone(Client c){
        client = c;
    }

    //LONG CMDs
    public void start(){
        this.command("takeoff");
    }
    public void land(){
        this.command("land");
    }
    public void connect(){
        this.command("command");
    }

    @Override
    public boolean isConnected() {
        return client.isConnected();
    }

    @Override
    public void command(String cmd) {
        if(null == cmd || 0 == cmd.length())
            return; //"empty command";
        if(!client.isConnected()) {
            return; //"disconnected";
        }
        final byte[] sendData = cmd.getBytes();
        final DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.getServer(), client.getPort());
        client.send(sendPacket);
        System.out.println("Sent command: " + cmd);
    }

    @Override
    public boolean command(String cmd, int x) {
        return false;
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
    public boolean up(int x) {
        return false;
    }

    @Override
    public boolean down(int x) {
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
