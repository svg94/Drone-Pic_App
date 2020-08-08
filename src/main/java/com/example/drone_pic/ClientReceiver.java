package com.example.drone_pic;

public interface ClientReceiver extends Client, Runnable{
    String getStates();
    void setStates(String pStates);
}
