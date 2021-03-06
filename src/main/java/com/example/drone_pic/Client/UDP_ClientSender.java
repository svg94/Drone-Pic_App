package com.example.drone_pic.Client;

import com.example.drone_pic.Client.ClientSender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class UDP_ClientSender implements ClientSender {
    private int port;       //Tello 8889
    private String servername;  //192.168.10.1
    private InetAddress server;
    private DatagramSocket socket;

    public UDP_ClientSender(int pPort, String pServername) {
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
    /*public void sendCommand(String cmd){
        if(null == cmd || 0 == cmd.length())
            return; //"empty command";
        if(!socket.isConnected()) {
            return; //"disconnected";
        }
        final byte[] sendData = cmd.getBytes();
        final DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, server, port);
        try {
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Tello " + cmd);
    }*/
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
    public DatagramSocket getSocket(){
        return socket;
    }

}
