package com.example.drone_pic.Client;

public interface ClientReceiver extends Client, Runnable{
    String getStates();
    void setStates(String pStates);
}
