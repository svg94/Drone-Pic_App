package com.example.drone_pic.Drone;

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
    void backFlip();
    void up(int x);
    void down(int x);
    void left(int x);
    void right(int x);
    void forward(int x);
    void back(int x);
    void turnRight(int x);
    void turnLeft(int x);
    int getPort();
    String getIP();
    //String getAddress();
}

