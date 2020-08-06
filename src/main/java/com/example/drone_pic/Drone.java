package com.example.drone_pic;

public interface Drone {
    boolean isConnected();
    void command(String cmd);
    boolean command(String cmd, int x);
    int getBattery();
    int getSpeed();
    void setSpeed(int speed);
    int getTime();
    boolean backFlip();
    boolean up(int x);
    boolean down(int x);
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

