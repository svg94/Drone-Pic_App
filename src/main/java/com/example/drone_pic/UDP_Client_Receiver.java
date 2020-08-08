package com.example.drone_pic;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Client_Receiver extends Thread implements ClientReceiver {
    boolean running;
    String states;

    int port;
    String servername;
    InetAddress ip;
    DatagramSocket socket;

    public UDP_Client_Receiver(){
        this.states = "starting";
    }

    public void run()
    {
        running = true;
        while(running){
            DatagramPacket recData;
            setStates("");
        }
    }


    @Override
    public String getStates() {
        return states;
    }
    public void setStates(String pStates){
        this.states = pStates;
    }

    @Override
    public void connect() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public InetAddress getServer() {
        return null;
    }


    @Override
    public DatagramSocket getSocket() {
        return socket;
    }
}
