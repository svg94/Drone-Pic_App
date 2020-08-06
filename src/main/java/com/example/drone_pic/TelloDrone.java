package com.example.drone_pic;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
public class TelloDrone implements Drone {
    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean command(String cmd) {
        return false;
    }

    @Override
    public boolean command(String cmd, int x) {
        return false;
    }

    @Override
    public int getBattery() {
        return 0;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed(int speed) {

    }

    @Override
    public int getTime() {
        return 0;
    }

    @Override
    public boolean backFlip() {
        return false;
    }

    @Override
    public boolean up(int x) {
        return false;
    }

    @Override
    public boolean down(int x) {
        return false;
    }

    @Override
    public boolean left(int x) {
        return false;
    }

    @Override
    public boolean right(int x) {
        return false;
    }

    @Override
    public boolean forward(int x) {
        return false;
    }

    @Override
    public boolean back(int x) {
        return false;
    }

    @Override
    public boolean turnRight(int x) {
        return false;
    }

    @Override
    public boolean turnLeft(int x) {
        return false;
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public void setPort() {

    }

    @Override
    public String getIP() {
        return null;
    }
}
