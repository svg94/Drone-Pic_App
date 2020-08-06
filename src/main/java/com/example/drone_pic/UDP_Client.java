package com.example.drone_pic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class UDP_Client {
    private int port;
    private String servername;
    private InetAddress server;
    private DatagramSocket socket;

    public UDP_Client(int pPort, String pServername) {
        this.port = pPort;
        this.servername = pServername;
        try {
            this.server = InetAddress.getByName(servername);
            socket = new DatagramSocket(port);
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch (SocketException e) {
            e.printStackTrace();
        }

    }
    public void connect(){
        socket.connect(server,port);
    }
    public boolean isConnected(){
        if(socket == null){
            return false;
        }
        return socket.isConnected();
    }
    public void close(){
        socket.close();
    }

    public void send(DatagramPacket data){
        try {
            socket.send(data);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getPort(){
        return this.port;
    }
    public InetAddress getServer(){
        return server;
    }

}