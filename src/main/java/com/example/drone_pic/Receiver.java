package com.example.drone_pic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Receiver extends Thread {
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[2048];
    private int port;
    public void listen()
    {
        running = true;
        while(running){
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                this.handle(packet.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

    public void handle(byte[] data){

    }
    public void run(){
        listen();
    }
    public void close(){
        running = false;
    }
}
