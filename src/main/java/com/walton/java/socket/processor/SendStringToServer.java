package com.walton.java.socket.processor;

import poisondog.core.Mission;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class SendStringToServer implements Mission<String>{
    private Socket socket;
    private DataOutputStream dataOutputStream;
    public SendStringToServer(Socket socket){
        this.socket = socket;
    }
    public Boolean execute(String string){
        try{
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(string);
            dataOutputStream.flush();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public void close(){
        try {
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
