package com.example.drone_pic;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public interface ClientSender extends Client {

    void send(DatagramPacket data);

    //void sendCommand(String cmd);
}
