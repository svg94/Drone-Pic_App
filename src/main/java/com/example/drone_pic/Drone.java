package com.example.drone_pic;

import java.io.IOException;

public interface Drone {
    void connect();
    void start();
    void land();

    boolean isConnected();
    void command(String cmd, double time);
    int getBattery();
    int getSpeed();
    void setSpeed(int speed);
    int getTime();
    boolean backFlip();
    void up(int x);
    void down(int x);
    boolean left(int x);
    boolean right(int x);
    boolean forward(int x);
    boolean back(int x);
    boolean turnRight(int x);
    boolean turnLeft(int x);
    int getPort();
    String getIP();
    //String getAddress();
}

