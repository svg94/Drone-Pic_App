package com.example.drone_pic;

import java.net.DatagramPacket;
import java.net.InetAddress;

public interface Client {
    public void connect();
    public boolean isConnected();
    public void close();
    public int getPort();
    public InetAddress getServer();
    public void send(DatagramPacket data);
}
