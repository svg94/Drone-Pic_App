package com.example.drone_pic.Client;

import java.net.DatagramPacket;

public interface ClientSender extends Client {

    void send(DatagramPacket data);

    //void sendCommand(String cmd);
}
