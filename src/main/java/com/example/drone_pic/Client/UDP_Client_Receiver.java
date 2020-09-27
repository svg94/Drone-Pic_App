package com.example.drone_pic.Client;

import com.example.drone_pic.Client.ClientReceiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDP_Client_Receiver extends Thread implements ClientReceiver {
    boolean running;
    String states;

    int port;
    String servername;
    InetAddress ip;
    DatagramSocket socket;
    byte[] buf = new byte[2048];

    public UDP_Client_Receiver(int port, String servername){
        this.states = "starting";
        this.port = port;
        this.servername = servername;
        try {
            this.ip = InetAddress.getByName(servername);
            this.socket = new DatagramSocket(this.port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run()
    {
        this.connect();
        running = true;
        setStates("running..");
        while(running){
            try {

                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);         //RECEIVE DOESNT WORK YET.
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);

                //Handle String State Data of Tello.
                setStates(new String(packet.getData()));
                System.out.println(getStates());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setStates("closed.");
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
        socket.connect(ip,port);
    }

    @Override
    public boolean isConnected() {
        if(socket == null)
            return false;
        return socket.isConnected();
    }

    @Override
    public void close() {

    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public InetAddress getServer() {
        return ip;
    }


    @Override
    public DatagramSocket getSocket() {
        return socket;
    }
}
