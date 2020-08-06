package com.example.drone_pic;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Connector {
    private InetAddress ip = null;
    private int port;
    private DatagramSocket s;




    final public void connect(final String strIP, final int port) throws Exception {
        this.port = port;
        ip = InetAddress.getByName(strIP);
        s = new DatagramSocket(port);
        s.connect(ip, port);
        sendCommand("command");
        System.out.println("//////////////////////////");
        System.out.println(s.getLocalPort());
        System.out.println(s.getLocalSocketAddress());
        System.out.println(s.getLocalAddress());
        System.out.println(s.getPort());
        System.out.println(s.getRemoteSocketAddress());
        System.out.println(s.getReuseAddress());
        System.out.println(s.getReceiveBufferSize());
        System.out.println(s.getSendBufferSize());
        System.out.println(s.getTrafficClass());
        System.out.println("//////////////////////////");
    }
    public void start() throws IOException {
        confirmationCommand("takeoff");
    }
    public void land() throws IOException {
        confirmationCommand("land");
    }
    final private void sendCommand(final String strCommand) throws IOException {
        if(null == strCommand || 0 == strCommand.length())
            return; //"empty command";
        if(!s.isConnected()) {
            return; //"disconnected";
        }
        //byte[] receiveData = new byte[1024];
        final byte[] sendData = strCommand.getBytes();
        final DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
        s.send(sendPacket);
        //final DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        //s.receive(receivePacket);
        //final String ret = new String(receivePacket.getData());
        System.out.println("Tello " + strCommand); //+ ": " + ret);
        //return ret;
    }



    public String sendAndReceiveCommand(String command, int length) throws IOException {
        this.sendCommand(command);
        return this.receiveMessage(length);
    }

    public String sendAndReceiveCommand(String command) throws IOException {
        this.sendCommand(command);
        return this.receiveMessage();
    }

    public boolean confirmationCommand(String command) throws IOException {
        String response = sendAndReceiveCommand(command);
        System.out.println(response.length());
        if(response.equalsIgnoreCase("ok")) {
            return true;
        }
        return false;
    }
    public String receiveMessage() {
        return this.receiveMessage(1024);
    }

    public String receiveMessage(int length) {
        byte[] receiveData = new byte[1024];
        final DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            s.receive(receivePacket);
            return trimExecutionResponse(receiveData, receivePacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String trimExecutionResponse(byte[] response, DatagramPacket receivePacket) throws UnsupportedEncodingException {
        response = Arrays.copyOf(response, receivePacket.getLength());
        return new String(response, "UTF-8");
    }
}
