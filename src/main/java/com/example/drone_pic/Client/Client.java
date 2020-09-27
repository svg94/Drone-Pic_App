package com.example.drone_pic.Client;

import java.net.DatagramSocket;
import java.net.InetAddress;

public interface Client {
    void connect();
    boolean isConnected();
    void close();
    int getPort();
    InetAddress getServer();
    DatagramSocket getSocket();
}
